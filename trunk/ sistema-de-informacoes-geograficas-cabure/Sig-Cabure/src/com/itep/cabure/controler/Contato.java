package com.itep.cabure.controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Contato
 */

public class Contato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Contato() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String uf = request.getParameter("uf");
		String cidade = request.getParameter("cidade");
		String prof = request.getParameter("prof");
		
		
		System.out.println("Estamos aqui!!!");
		System.out.println(nome);
		System.out.println(email);
		System.out.println(uf);
		System.out.println(cidade);
		System.out.println(prof);
		
		
		
		response.sendRedirect("respostaContato.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/jsp;charset=iso-8859-1");
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String uf = request.getParameter("uf");
		String cidade = request.getParameter("cidade");
		String prof = request.getParameter("prof");
		
		
		System.out.println("Estamos aqui!!!");
		System.out.println(nome);
		System.out.println(email);
		System.out.println(uf);
		System.out.println(cidade);
		System.out.println(prof);
		
		
		
		response.sendRedirect("respostaContato.jsp");
		
		
		
		
		
		
	}

}
