package com.itep.cabure.controler;

import com.itep.cabure.dao.GenericDAO;
import com.itep.cabure.entidades.Empresa;
import com.itep.cabure.entidades.Grupo;
import com.itep.cabure.entidades.Pessoa;
import com.itep.cabure.entidades.Usuario;
import com.itep.cabure.entidades.Valida;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Processamento do controle de acesso para tratamento da autenticação dos
 * usuários.
 *
 * @author Fernanda.franklin
 * @version 1.3?
 * @since 13/10/2014
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        HttpSession httpSession = request.getSession(true);
        httpSession.setMaxInactiveInterval(600);

        try {
            String login = request.getParameter("o-cpf-cnpj"); // AUTENTICAR
            String oSenha = request.getParameter("senha");

            if (login != null) {
                GenericDAO<Usuario> usuarioDAO = new GenericDAO<Usuario>();

                Usuario usuarioBuscado = new Usuario();
                Usuario usuario = (Usuario) usuarioDAO.acessaRegistro(usuarioBuscado, "usuario", login);
  
                if (usuario != null && usuario.getSenha().equals(oSenha)) {
                    LogControler log = new LogControler();
                    
                    // <editor-fold defaultstate="collapsed" desc="Verifica o status da Flag do usuário e  faz as devidas alterações, quanto esta for diferente de AUTENTICADA">
                    Valida usuarioAtivo = log.getValidaUsuario(usuario);
                    FlagStatus flagStatus = log.getFlagStatus(usuarioAtivo.getFlag());
                    switch (flagStatus) {
                        case ESPERANDO_AUTENTICACAO:
                            response.sendRedirect("contaNaoAtivada.jsp");
                            break;
                        case SOLICITACAO_NOVA_SENHA:
                            log.setValida(null, usuarioAtivo, FlagStatus.ATIVO);
                            break;
                        case BLOQUEIO_CONTA:
                            response.sendRedirect("usuarioBloqueado.jsp");
                            break;
                    }
                    // </editor-fold>
                    
                    // <editor-fold defaultstate="collapsed" desc="Retorna o grupo do Usuário">
                    GenericDAO<Grupo> grupoDAO = new GenericDAO();

                    Grupo grupoUser = new Grupo();
                    Grupo grupoUsuario = (Grupo) grupoDAO.acessaRegistro(grupoUser, "nomeGrupo", usuario.getGrupo().getNomeGrupo());

                    grupoDAO.fechar();
                    // </editor-fold>
                    
                    if (grupoUsuario.getNomeGrupo().equals("Administrador de TI")) {
                        // Autenticação do administrador
                        log.setLog(usuario, PadroesLog.LOGIN);

//                        httpSession.setAttribute("SESSION_AUTORIZADO", "Administrador autorizado");
//                        httpSession.setAttribute("SESSION_IDENTIFICADOR_ADMIN", oPessoa.getCpf());
//                        response.sendRedirect("in/ad/");
                    } else if (grupoUsuario.getNomeGrupo().equals("Colaborador")) {
                        // Autenticação Colaborador
                        Pessoa pessoaLogada = log.getPessoaLogada(usuario);
                        log.setLog(usuario, PadroesLog.LOGIN);
                        httpSession.setAttribute("SESSION_IDENTIFICADOR_LOGADO", pessoaLogada.getCpf());

                    	response.sendRedirect("index.jsp");
                    } else if (grupoUsuario.getNomeGrupo().equals("Técnico")) {
                        // Autenticação Técnico
                        Pessoa pessoaLogada = log.getPessoaLogada(usuario);
                        log.setLog(usuario, PadroesLog.LOGIN);

                        // FALTA DIRECIONAMENTO DA PÁGINA
                    } else if (grupoUsuario.getNomeGrupo().equals("Empreendedor")) {
                        // Autenticação Empreendedor
                        String tipoUsuario = log.getTipoUsuario(usuario.getUsuario());

                        if (tipoUsuario == "CNPJ") {
                            // Empreendedor Pessoa Jurídica
                            Empresa empresaLogada = log.getEmpresaLogada(usuario);
                            log.setLog(usuario, PadroesLog.LOGIN);
                            httpSession.setAttribute("SESSION_IDENTIFICADOR_LOGADO", empresaLogada.getCnpj());
                        } else if (tipoUsuario == "CPF") {
                            // Empreendedor Pessoa Física
                            Pessoa pessoaLogada = log.getPessoaLogada(usuario);
                            log.setLog(usuario, PadroesLog.LOGIN);
                            httpSession.setAttribute("SESSION_IDENTIFICADOR_LOGADO", pessoaLogada.getCpf());
                        }
                        response.sendRedirect("index.jsp");
                    } else {
                        response.sendRedirect("acesso.jsp?err=Você não é um usu&aacute;rio cadastrado");
                    }
                } else {
                    response.sendRedirect("acesso.jsp?err=Usuário ou senha incorretos. Tente novamente!");
                }
                usuarioDAO.fechar();
            } else {
                System.out.println("Por favor, insira as informações de login e senha!");
            }
        } catch (IOException ex) {
            System.out.println("lala: " + ex.toString());
        } catch (NullPointerException nulPoint) {
            httpSession.setAttribute("sessao", "Usuario");
            response.sendRedirect("/itep-1/in/erro.jsp?err="
                    + nulPoint.toString());
        }
    }

    // <editor-fold defaultstate="collapsed"
// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     *
     * Setando o dia
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}