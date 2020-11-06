package com.todoapp.web;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.todoapp.conn.connection_db;
import com.todoapp.model.*;
import com.todoapp.dao.*;


public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("Registerjsp.jsp");	
	}	
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		   String firstname=request.getParameter("firstname");
		   String lastname=request.getParameter("lastname");
		   String username=request.getParameter("username");
		   String password=request.getParameter("password");
		   
		
		   User user=new User();
		   user.setFirstName(firstname);
		   user.setLastName(lastname);
		   user.setUsername(username);
		   user.setPassword(password);
		   
		   UserDao u=new UserDao();
		   
		   try {
	            int result = u.registeremployee(user);
	            if (result == 1) {
	                request.setAttribute("NOTIFICATION", "User Registered Successfully!");
	                
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		   
		   RequestDispatcher dispatcher = request.getRequestDispatcher("/Registerjsp.jsp");
	       dispatcher.forward(request, response);

	}

}
