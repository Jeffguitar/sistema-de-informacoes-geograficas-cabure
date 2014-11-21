package com.itep.cabure.controler;

import com.itep.cabure.dao.GenericDAO;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itep.cabure.dao.ValidaDAO;
import com.itep.cabure.entidades.*;

import java.util.List;

/**
 * Servlet implementation class Administracao
 */
public class Administracao extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * Classe responsï¿½vel pela persistencia do dado do usuï¿½rio e
     * encaminhamento dos dados de email.
     *
     * @param request servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        try {
            String acao = request.getParameter("acao");
            if (acao != null) {
                if (acao.equals("excluir")) {//ecluir usuario permanentemente
                    processaRemocaoDeUsuario(request, response);
                } else if (acao.equals("bloquear")) {//bloquear acesso de usuario
                    processaBloqueioDoUsuario(request, response);
                } else if (acao.equals("PesquisaEntidades")) {
                    processaPesquisaAtores(request, response);
                }
            } else {
                response.sendRedirect("in/ad/index.jsp?err=Pessoa inexistente");
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     *
     * Metodo responsável por remover os usuários do sistema de maneira atômica.
     *
     * @param request
     * @param response
     */
    private void processaRemocaoDeUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String identificador = request.getParameter("identificadorUsuario");//cpf ou cnpj
        if (identificador != null) {
            GenericDAO<Usuario> daoUsuario = new GenericDAO();
            GenericDAO<Valida> daoValida = new GenericDAO();

            GenericDAO<Pessoa> daoPessoa = new GenericDAO();
            GenericDAO<Empresa> daoEmpresa = new GenericDAO();

            GenericDAO<Endereco> daoEndereco = null;
            GenericDAO<Contato> daoContato = null;

            boolean itsOk = true;
            boolean isLockPerfil = false;

            // <editor-fold defaultstate="collapsed" desc="remove registros da tabela valida">
            /* PROBLEMA COM O HIBERNTE VARER IDUSUARIO STRING
             Valida validaBuscado = new Valida();
             Valida valida = (Valida) daoValida.acessaRegistro(validaBuscado, "idUsuario", identificador);
             if (valida != null) {
             daoValida.remover(valida);
             daoValida.fechar();
             } else {
             System.out.println("Problema ao remover valida");
             itsOk = false;
             }
             */
            ValidaDAO daoVl = new ValidaDAO();
            Valida vl = daoVl.busca(identificador);
            if (vl != null) {
                daoVl.remove(vl);
                daoVl.fechar();
            } else {
                System.out.println("erro valida delecao");
            }

            // <editor-fold defaultstate="collapsed" desc="remove registros da tabela usuario">
            Usuario usuarioBuscado = new Usuario();
            Usuario usuario = (Usuario) daoUsuario.acessaRegistro(usuarioBuscado, "usuario", identificador);
            if (usuario != null) {
                daoUsuario.remover(usuario);
                daoUsuario.fechar();
            } else {
                System.out.println("Problema na remoção do Usuario");
                itsOk = false;
            }
            // </editor-fold>

            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="remove registros da tabela pessoa contato endereco">
            Pessoa pessoaBuscado = new Pessoa();
            Pessoa pessoa = (Pessoa) daoPessoa.acessaRegistro(pessoaBuscado, "cpf", identificador);
            if (pessoa != null) {
                daoPessoa.remover(pessoa);
                daoPessoa.fechar();
                /* PROBLEMA LOGICO
                 daoContato = new GenericDAO();
                 Contato contatoBuscado = new Contato();
                 Contato contato = (Contato) daoContato.acessaRegistro(contatoBuscado, "", identificador);
                 if (contato != null) {
                 daoContato.remover(contato);
                 daoContato.fechar();
                 } else {
                 System.out.println("Problema na remoção da contato");
                 itsOk = false;
                 }
                 daoEndereco = new GenericDAO();
                 Endereco enderecoBuscado = new Endereco();
                 Endereco endereco = (Endereco) daoEndereco.acessaRegistro(enderecoBuscado, "", identificador);
                 if (endereco != null) {
                 daoEndereco.remover(endereco);
                 daoEndereco.fechar();
                 } else {
                 System.out.println("Problema na remoção da pessoa");
                 itsOk = false;
                 }
                 */
                isLockPerfil = true;
                response.sendRedirect("in/ad/usuariosGrupos.jsp?ok=" + pessoa.getNome() + " removida com sucesso");
            } else {
                System.out.println("Problema na remoção da pessoa");
                itsOk = false;
            }
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="remove registros da tabela empresa contato endereco">
            Empresa empresaBuscada = new Empresa();
            Empresa empresa = (Empresa) daoEmpresa.acessaRegistro(empresaBuscada, "cnpj", identificador);
            if (empresa != null && isLockPerfil == false) {
                daoEmpresa.remover(empresa);
                daoEmpresa.fechar();
                /* PROBLEMA LOGICO
                 daoContato = new GenericDAO();
                 Contato contatoBuscado = new Contato();
                 Contato contato = (Contato) daoContato.acessaRegistro(contatoBuscado, "", identificador);
                 if (contato != null) {
                 daoContato.remover(contato);
                 daoContato.fechar();
                 } else {
                 System.out.println("Problema na remoção da contato empresa");
                 itsOk = false;
                 }
                 daoEndereco = new GenericDAO();
                 Endereco enderecoBuscado = new Endereco();
                 Endereco endereco = (Endereco) daoEndereco.acessaRegistro(enderecoBuscado, "email", identificador);
                 if (endereco != null) {
                 daoEndereco.remover(endereco);
                 daoEndereco.fechar();
                 } else {
                 System.out.println("Problema na remoção da pessoa");
                 itsOk = false;
                 }
                 */
                isLockPerfil = true;
                response.sendRedirect("in/ad/usuariosGrupos.jsp?ok=" + empresa.getRazaoSocial() + " removida com sucesso");
            } else {
                System.out.println("Problema na remoção da empresa");
                itsOk = false;
            }
            // </editor-fold>
        } else {
            response.sendRedirect("in/ad/usuariosGrupos.jsp?ok=Identificador nulo");
        }
    }

    /**
     *
     * @param request
     * @param response
     */
    private void processaBloqueioDoUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String identificador = request.getParameter("identificadorUsuario");
        response.sendRedirect("in/ad/?ok=usuario bloqueado com sucesso");
    }

    private void processaPesquisaAtores(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession httpSession = request.getSession(true);
        httpSession.setMaxInactiveInterval(12000);
        String campoDeBusca = request.getParameter("campoDeBusca");
        if (campoDeBusca != null) {
            GenericDAO<Pessoa> pessoaDAO = new GenericDAO();
            GenericDAO<Empresa> empresaDAO = new GenericDAO();

            Pessoa acessaPessoa = new Pessoa();
            Empresa acessaEmpresa = new Empresa();

            List<Pessoa> pessoas = (List<Pessoa>) pessoaDAO.acessaRegistrosLike(acessaPessoa, "nome", "%" + campoDeBusca + "%", "nome");
            List<Empresa> empreendedores = (List<Empresa>) empresaDAO.acessaRegistrosLike(acessaEmpresa, "razaoSocial", "%" + campoDeBusca + "%", "razaoSocial");

            if (pessoas != null) {
                httpSession.setAttribute("buscaEntidadePessoa", pessoas);
            } else {
                httpSession.setAttribute("buscaEntidadePessoa", null);
            }

            if (empreendedores != null) {
                httpSession.setAttribute("buscaEntidadeEmpreendedor", empreendedores);
            } else {
                httpSession.setAttribute("buscaEntidadeEmpreendedor", null);
            }

            response.sendRedirect("in/ad/resultadoEntidades.jsp");
        } else {
            httpSession.setAttribute("buscaEntidadePessoa", null);
            httpSession.setAttribute("buscaEntidadeEmpreendedor", null);
            response.sendRedirect("in/ad/resultadoEntidades.jsp");
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
