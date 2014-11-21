package com.itep.cabure.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para tratar o fluxo de informações sobre permissões de acesso ao
 * sistema
 *
 * @author macario
 */
public class PermissaoAcesso extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        response.setContentType("text/html;charset=iso-8859-1");
        response.setCharacterEncoding("ISO-8859-1");
        String tipo_form = request.getParameter("tf");
        System.out.println("Time 1");
        try {
            String oEmail = request.getParameter("emailPaciente");
            System.out.println("Time 2");
            if (oEmail != null) {
                /*TodosDAO daoAcesso = new TodosDAO();
                 Todos acesso = daoAcesso.busca(oEmail);
                 System.out.println("Time 3");
                 if (acesso != null && tipo_form.equals("Bloqueia")) {
                 System.out.println("Time 4");
                 acesso.setPermissaoAcesso("bloqueado");
                 daoAcesso.salvar(acesso);
                 daoAcesso.fechar();
                 response.sendRedirect("in/3278/pacientes.jsp");
                 } else if (acesso != null && tipo_form.equals("Libera")) {
                 System.out.println("Time 5");
                 acesso.setPermissaoAcesso("permitido");
                 daoAcesso.salvar(acesso);
                 daoAcesso.fechar();
                 response.sendRedirect("in/3278/pacientes.jsp");
                 } else {
                 System.out.println("Time 6");
                 response.sendRedirect("in/3278/pacientes.jsp?err=Não foi possivel");
                 daoAcesso.fechar();
                 }*/
                System.out.println("Time 1");
            }
        } catch (Exception ex) {
            System.out.println("ex: " + ex.toString());
        }
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
