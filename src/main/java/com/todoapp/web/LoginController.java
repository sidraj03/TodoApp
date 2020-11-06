package com.todoapp.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import com.todoapp.dao.LoginDao;
import com.todoapp.model.LoginBean;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	    response.sendRedirect("login.jsp");
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		LoginBean login=new LoginBean();
		LoginDao dao=new LoginDao();
		
		login.setUsername(username);
		login.setPassword(password);
		
		try {
			if(dao.loginUser(login)) {
				System.out.println("Success");
				HttpSession session=request.getSession();
 			    session.setAttribute("username", username);
				RequestDispatcher dispatcher=request.getRequestDispatcher("/list");
				dispatcher.forward(request,response);
			}
			else {
				System.out.println("invalid login");
				//HttpSession session=request.getSession();
				//session.setAttribute("user",username);
				//response.sendRedirect("login.jsp");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

	}
}
