package com.itep.cabure.controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itep.cabure.email.SendEmailMultiThread;

import com.itep.cabure.dao.GenericDAO;
import com.itep.cabure.entidades.Pessoa;
import com.itep.cabure.entidades.Contato;
import com.itep.cabure.entidades.Empresa;
import com.itep.cabure.entidades.Usuario;
import com.itep.cabure.entidades.Valida;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletContext;

/**
 *
 * Servlet que trata o processamento de recuperaÃ§Ã£o dos dados de acesso do
 * usuÃ¡rio de acordo com o estagio de de aÃ§Ã£o solicitado.
 *
 * @author macario.granja
 * @since 02/09/2014
 * @version 0.2?
 */
public class RecuperarSenha extends HttpServlet {

    public String ipServer = "200.17.134.46";
    //public String ipServer = "localhost";

    /**
     *
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
        httpSession.setMaxInactiveInterval(12000);

        String opcao = request.getParameter("acao");
        try {
            LogControler logControler = new LogControler();

            if (opcao.equals("busca")) {
                // <editor-fold defaultstate="collapsed" desc="Processa a busca dos dados do usuario e envia email para cadastrar nova senha">
                String oIdentificador = request.getParameter("recover_cpf");
                if (oIdentificador != null) {
                    //<editor-fold defaultstate="collapsed" desc="Verifica se CPF ou CNPJ existe na base">
                    GenericDAO<Usuario> usuarioDAO = new GenericDAO<Usuario>();

                    Usuario usuario = new Usuario();
                    Usuario usuarioBuscado = (Usuario) usuarioDAO.acessaRegistro(usuario, "usuario", oIdentificador);

                    usuarioDAO.fechar();
                    // </editor-fold>  

                    if (usuarioBuscado != null) {
                        httpSession.setAttribute("PageView", "ok");
                        // Verifica se o usuâ³©o Pessoa FÃ­sica ou Jurî¥©ca
                        String tipoUsuario = logControler.getTipoUsuario(usuarioBuscado.getUsuario());

                        if (tipoUsuario == "CPF") {
                            //<editor-fold defaultstate="collapsed" desc="Busca dados e Processa envio Email para Recuperar Senha - Pessoa Física">
                            Pessoa pessoa = logControler.getPessoaLogada(usuarioBuscado);
                            Valida validaUsuario = logControler.getValidaUsuario(usuarioBuscado);
                            logControler.setValida(null, validaUsuario, FlagStatus.SOLICITACAO_NOVA_SENHA);
                            logControler.setLog(usuarioBuscado, PadroesLog.NOVA_SENHA_SOLICITADA);

                            processaEnvioEmailRestauracaoSenhaPessoa(pessoa, validaUsuario, request);
                            
                            // </editor-fold>
                        } else {
                            //<editor-fold defaultstate="collapsed" desc="Busca dados e Processa envio Email para Recuperar Senha - Pessoa Jurídica">
                            Empresa empresa = logControler.getEmpresaLogada(usuarioBuscado);
                            Valida validaUsuario = logControler.getValidaUsuario(usuarioBuscado);
                            logControler.setValida(null, validaUsuario, FlagStatus.SOLICITACAO_NOVA_SENHA);
                            logControler.setLog(usuarioBuscado, PadroesLog.NOVA_SENHA_SOLICITADA);

                            processaEnvioEmailRestauracaoSenhaEmpresa(empresa, validaUsuario, request);
       
                            // </editor-fold>
                        }

                        response.sendRedirect("sucesso.jsp");

                    } else {
                        response.sendRedirect("recover.jsp?err=Usuï¿½rio inexitente tente novamente.");
                    }
                } else {
                    response.sendRedirect("recover.jsp?err=Identificador inexistente.");
                }

                // </editor-fold>
            } else if (opcao.equals("cadastraNovaSenha")) {
                // <editor-fold defaultstate="collapsed" desc="Registra as alterações de inserção da nova senha">

                // Retorna o token do link do email enviado para o usuário
                String token = request.getParameter("tokenValue");

                if (token != null) {

                    // Retorna a instância de valida vinculado ao token extraído do parâmetro do servlet
                    Valida valida = logControler.getTokenValida(token);

                    // Retorna o usuário vinculado à valida
                    Usuario usuarioBuscado = logControler.getUsuarioValida(valida.getIdUsuario().getUsuario());
                    
                    // Invalida o token para que a página seja expirada
                    logControler.invalidaToken(valida);

                    String tipoUsuario = logControler.getTipoUsuario(usuarioBuscado.getUsuario());

                    if ("CPF".equals(tipoUsuario)) { // Pessoa Fî´©ca
                        // <editor-fold defaultstate="collapsed" desc="Processa alteração da senha para o Usuário Pessoa Física">                        
                        Pessoa pessoa = logControler.getPessoaLogada(usuarioBuscado);

                        if (pessoa != null) {
                            // <editor-fold defaultstate="collapsed" desc=" Aqui a senha do usuário Pessoa Física será alterada">
                            String senha = request.getParameter("oSenha");
                            String senhaRepetida = request.getParameter("oSenhaRepeticao");

                            if (senha.equals(senhaRepetida)) {
                                GenericDAO<Usuario> upUsuarioDAO = new GenericDAO<Usuario>();

                                usuarioBuscado.setSenha(senha);
                                upUsuarioDAO.alterar(usuarioBuscado);
                                upUsuarioDAO.fechar();
                                
                                logControler.setValida(null, valida, FlagStatus.ATIVO);

                                logControler.setLog(usuarioBuscado, PadroesLog.SENHA_ALTERADA);

                                // httpSession.setAttribute("SESSION_EMAIL_LOGADO", pessoa.getCpf());
                                response.sendRedirect("confirmacaoNovosDados.jsp");
                                // </editor-fold>
                            } else {
                                response.sendRedirect("recuperarDados.jsp?err=Senhas diferentes. Tente novamente!");
                            }
                        } else {
                            response.sendRedirect("recuperarDados.jsp?err=Usuï¿½rio inexistente");
                        }
                        // </editor-fold>
                    } else { // Pessoa Jurî¥©ca
                        Empresa empresa = logControler.getEmpresaLogada(usuarioBuscado);

                        if (empresa != null) {
                            // <editor-fold defaultstate="collapsed" desc="Processa alteração da senha para o Usuário Pessoa Jurídica"> 
                            String senha = request.getParameter("oSenha");
                            String senhaRepetida = request.getParameter("oSenhaRepeticao");

                            if (senha.equals(senhaRepetida)) {
                                GenericDAO<Usuario> upUsuarioDAO = new GenericDAO<Usuario>();
                                usuarioBuscado.setSenha(senha);
                                upUsuarioDAO.alterar(usuarioBuscado);
                                upUsuarioDAO.fechar();
                                
                                logControler.setValida(null, valida, FlagStatus.ATIVO);
                                
                                logControler.setLog(usuarioBuscado, PadroesLog.SENHA_ALTERADA);

                                //httpSession.setAttribute("SESSION_EMAIL_LOGADO", empresa.getCnpj());
                                response.sendRedirect("confirmacaoNovosDados.jsp");
                                // </editor-fold>
                            } else {
                                response.sendRedirect("recuperarDados.jsp?err=Senhas diferentes. Tente novamente!");
                            }
                        } else {
                            response.sendRedirect("recuperarDados.jsp?err=Usuï¿½rio inexistente");
                        }
                    }

                }

                // </editor-fold>
            }
        } catch (Exception ex) {
            System.out.println("Erro de Alteração de Senha" + ex.toString());
        }
    }

    /**
     * Mï¿½todo para envio de email para usuï¿½rio que se cadastra
     *
     * @param Pessoa oPessoa
     * @since 27/08/2014
     */
    private void processaEnvioEmailRestauracaoSenhaPessoa(Pessoa pessoaFisica, Valida valida, HttpServletRequest request) {

        String filePath = "/envioEmailRecSenha.html";
        File file;
        String emailMessage = "";
        try {
            // <editor-fold defaultstate="collapsed" desc="Declarações de servlets e String Builder para ler o HTML do email">
            ServletContext servletContext = request.getSession().getServletContext();
            String relativeWebPath = filePath;
            String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
            file = new File(absoluteDiskPath);

            FileInputStream is;
            is = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // </editor-fold>
            int count = 0;
            while ((line = reader.readLine()) != null) {
                count++;
                if (count == 38) {
                    sb.append(line).append(pessoaFisica.getNome());
                } else if (count == 50) {
                    sb.append(line).append("http://" + ipServer + "/cabure/recuperarDados.jsp?token=")
                            .append(valida.getToken());
                } else {
                    sb.append(line).append("\n");
                }
            }

            emailMessage = sb.toString();
            is.close();
        } catch (IOException ex1) {
            System.out.println("Certso: " + ex1);
        } catch (Exception xos) {
            System.out.println("Certso: " + xos);
        }

        Thread en;
        // <editor-fold defaultstate="collapsed" desc="Busca os dados de Contato para a Pessoa">
        GenericDAO<Contato> contatoDAO = new GenericDAO<Contato>();

        Contato contato = new Contato();
        Contato contatoBuscado = (Contato) contatoDAO.acessaRegistroObjeto(contato, "pessoa", pessoaFisica);
        // </editor-fold>

        en = new Thread(new SendEmailMultiThread(contatoBuscado.getEmail(), "Restauração de Senha - SIG Caburé", emailMessage));
        en.start();
    }

    private void processaEnvioEmailRestauracaoSenhaEmpresa(Empresa empresa, Valida valida, HttpServletRequest request) {

        String filePath = "/envioEmailRecSenha.html";
        File file;
        String emailMessage = "";
        try {
            // <editor-fold defaultstate="collapsed" desc="Declarações de servlets e String Builder para ler o HTML do email">
            ServletContext servletContext = request.getSession().getServletContext();
            String relativeWebPath = filePath;
            String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
            file = new File(absoluteDiskPath);

            FileInputStream is;
            is = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // </editor-fold>
            int count = 0;
            while ((line = reader.readLine()) != null) {
                count++;
                if (count == 38) {
                    sb.append(line).append(empresa.getRazaoSocial());
                } else if (count == 50) {
                    sb.append(line).append("http://" + ipServer + "/cabure/recuperarDados.jsp?token=")
                            .append(valida.getToken());
                } else {
                    sb.append(line).append("\n");
                }
            }

            emailMessage = sb.toString();
            is.close();
        } catch (IOException ex1) {
            System.out.println("Certso: " + ex1);
        } catch (Exception xos) {
            System.out.println("Certso: " + xos);
        }

        Thread en;

        // <editor-fold defaultstate="collapsed" desc="Busca os dados de Contato para a Empresa">
        GenericDAO<Contato> contatoDAO = new GenericDAO<Contato>();
        Contato contato = new Contato();
        Contato contatoBuscado = (Contato) contatoDAO.acessaRegistroObjeto(contato, "empresa", empresa);
        // </editor-fold>

        en = new Thread(new SendEmailMultiThread(contatoBuscado.getEmail(), "Restauração de Senha - SIG Caburé", emailMessage));
        en.start();

    }

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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
