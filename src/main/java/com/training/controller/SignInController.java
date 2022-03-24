package com.training.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.pms.dao.UsersDAO;
import com.training.pms.dao.UsersDAOImpl;

public class SignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public SignInController() {
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		
		// getting user's inputs
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		
		// testing if user is able to log in
		UsersDAO usersDAO = new UsersDAOImpl();
		boolean result = usersDAO.validate(username, password);
		
		if (result) { // successful login
//			session.setAttribute("message", "valid"); // have not set yet
			out.println("<h1>Welcome, " + username + "!</h1>");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
			dispatcher.include(request, response);
			
		} else { // unsuccessful login
//			session.setAttribute("message", "invalid"); // have not set yet

			out.println("<h1>Your username and/or password is incorrect, please try again.</h1>");
		}
		
		out.println("</body></html>");
	}
}
