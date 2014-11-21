/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itep.cabure.controler;

import com.itep.cabure.dao.GenericDAO;
import com.itep.cabure.entidades.LogSistema;
import com.itep.cabure.entidades.Usuario;
import com.itep.cabure.entidades.Valida;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Hibernate;

/**
 *
 * @author fernanda.franklin
 */
public class AutenticarConta extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        HttpSession httpSession = request.getSession(true);
        httpSession.setMaxInactiveInterval(12000);

        String token = request.getParameter("token");
        String requisicaoAcesso = request.getParameter("requisicaoAcesso");
        LogControler logControler = new LogControler();

        if (requisicaoAcesso == null) {
            Valida validaToken = logControler.getTokenValida(token);
            if (validaToken != null) {
                //Hibernate.initialize(validaToken);
                logControler.setValida(null, validaToken, FlagStatus.ATIVO);
                
                //***********DESCOMENTAR QUANDO TUDO ESTIVER OK
                logControler.invalidaToken(validaToken);
                Usuario usuario = logControler.getUsuarioValida(validaToken.getIdUsuario().getUsuario());

                logControler.setLog(usuario, PadroesLog.AUTENTICA_USUARIO);
                httpSession.setAttribute("SESSION_IDENTIFICADOR_LOGADO", usuario.getUsuario());
                response.sendRedirect("cadastroAutenticado.jsp");
            } else {
                response.sendRedirect("cadastroAutenticadoExpirado.jsp");
            }
        } else {
            response.sendRedirect("index.jsp");
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
