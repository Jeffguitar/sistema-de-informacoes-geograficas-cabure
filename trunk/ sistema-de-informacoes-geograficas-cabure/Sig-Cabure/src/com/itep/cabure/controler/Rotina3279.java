package com.itep.cabure.controler;

import com.itep.cabure.dao.PessoaDAO;
import com.itep.cabure.entidades.Pessoa;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Metodo necessario para efetuar o cadastro do primeiro administrador
 *
 * @author macario.granja
 * @since 24/09/2014
 * @version 0.1
 */
@WebServlet(name = "Rotina3279", urlPatterns = {"/Rotina3279"})
public class Rotina3279 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        /*
        try (PrintWriter out = response.getWriter()) {
            //Carregando requisicao
            String nome = request.getParameter("txt_nome_user");
            nome = letrasIniciaisMaiusculas(nome);
            String cpf = request.getParameter("txt_cpf");
            String email = request.getParameter("txt_email");
            String senhaNova = request.getParameter("senha");
            String senharepetida = request.getParameter("repSenha");

            PessoaDAO daoPessoa = new PessoaDAO();
            Pessoa cpfCadastrado = daoPessoa.buscaCpf(cpf);
            Pessoa emailCadastrado = daoPessoa.buscaEmail(email);

            if (cpfCadastrado == null) {
                if (emailCadastrado == null) {
                    if (nome != null) {
                        if (email != null) {
                            if (senhaNova.equals(senharepetida)) {
                                Pessoa p = new Pessoa();
                                p.setNome(nome);
                                p.setCpf(cpf);
                                p.setEmail(email);
                                p.setSenha(senhaNova);
                                p.setTipo("Administrador de TI");

                                daoPessoa.salvar(p);
                                daoPessoa.fechar();

                                HttpSession httpSession = request.getSession(true);
                                httpSession.setMaxInactiveInterval(12000);
                                httpSession.setAttribute("SESSION_AUTORIZADO", "Administrador autorizado");
                                httpSession.setAttribute("SESSION_IDENTIFICADOR_ADMIN", p.getCpf());

                                out.println("<!DOCTYPE html>");
                                out.println("<html>");
                                out.println("<head>");
                                out.println("<title>Caburé</title>");
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<h1>Olá administrador" + p.getNome() + "</h1>");
                                out.println("<h3>Esperamos que goste do seu sistema.</h3><br/>");
                                out.println("<a href='/cabure/in/ad/'> Acesse o Caburé </a>");
                                out.println("</body>");
                                out.println("</html>");
                            } else {
                                response.sendRedirect("in/ad/inicio.jsp?err=senhas são diferentes");
                            }
                        } else {
                            response.sendRedirect("in/ad/inicio.jsp?err=Email já cadastrado");
                        }
                    } else {
                        response.sendRedirect("in/ad/inicio.jsp?err=Preencha o nome");
                    }
                } else {
                    response.sendRedirect("in/ad/inicio.jsp?err=Este email já esta sendo utilizado");
                }
            } else {
                response.sendRedirect("in/ad/inicio.jsp?err=Você já é usuario");
            }
        } catch (Exception eex) {
            System.out.println("lala: " + eex.toString());
        }
        */
    }

    /**
     * Processa dados para habilitar as letra inicias de uma String para
     * maiusculas
     *
     * @param String nome
     * @since 27/08/2014
     */
    public String letrasIniciaisMaiusculas(String oNome) {
        String frase = oNome;
        frase = frase.toLowerCase();
        StringBuffer frase2 = new StringBuffer(frase);
        for (int i = 0; i < frase2.length(); i++) {
            Character letra = frase2.charAt(i);
            if (i == 0) {
                letra = Character.toUpperCase(letra);
                frase2.setCharAt(i, letra);
            } else if ((i > 0) && (frase2.charAt(i - 1) == ' ')) {
                letra = Character.toUpperCase(letra);
                frase2.setCharAt(i, letra);
            }
        }
        frase = frase2.toString();
        return frase;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    }// </editor-fold>

}