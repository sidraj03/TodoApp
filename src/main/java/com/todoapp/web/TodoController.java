package com.todoapp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.todoapp.dao.TodoDao;
import com.todoapp.dao.TodoDaoimpl;
import com.todoapp.model.Todo;

public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDao dao;
	
	public void init() {
		dao=new TodoDaoimpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doGet(request,response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String action=request.getServletPath();
         
         //options of various actions
         
         try {
        	 switch(action)
        	 {
        	 case "/new":
        		 newForm(request,response);
        		 break;
        	 case "/insert":
        		 insertTodo(request,response);
        		 break;
        	 case "/delete":
        		 deleteTodo(request,response);
        		 break;
        	 case "/edit":
        		 showEditForm(request,response);
        		 break;
        	 case "/update":
        		 updateTodo(request,response);
        	 case "/list":
        		 listTodo(request,response);
        		 break;
        	
        	//redirect to login page
            default:
        		//redirect to login page
        		RequestDispatcher dispatcher=request.getRequestDispatcher("/login.jsp");
        		dispatcher.forward(request, response);
        		break;
        	 }
         }
         
         catch(Exception ex)
         {
        	 ex.printStackTrace();
         }
         
	}
	
	private void listTodo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		//Retrieving username from session
		HttpSession session = request.getSession();
		String username=(String)session.getAttribute("username");

		List <Todo> listTodo=dao.selectAllTodos(username);
	
		for(Todo nme: listTodo){
			System.out.println(nme.getTitle());
		}
		
		request.setAttribute("listTodo",listTodo);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/todo-list.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	private void newForm(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher=request.getRequestDispatcher("/todo-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertTodo(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException,SQLException{
		
		//Retrieving username from session
		HttpSession session = request.getSession();
		String username=(String)session.getAttribute("username");
		
		//getting attributes from form
		String title=request.getParameter("title");
		String description=request.getParameter("description");
		boolean isDone=Boolean.valueOf(request.getParameter("isDone"));
		Todo newTodo=new Todo(title,username,description,LocalDate.now(),isDone);
		//inserting into the database
		dao.insertTodo(newTodo);
		//sending to list for display
        listTodo(request,response);
	}
	
	private void showEditForm(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		Todo existobj=dao.selectTodo(id);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/todo-form.jsp");
		request.setAttribute("todo", existobj);
		dispatcher.forward(request,response);
		
	}
	
	private void deleteTodo(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException, SQLException{
		int id=Integer.parseInt(request.getParameter("id"));
		dao.deleteTodo(id);
		listTodo(request,response);
	}
	
	private void updateTodo(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException, SQLException{
		int id=Integer.parseInt(request.getParameter("id"));
		//Retrieving username from session
		HttpSession session = request.getSession();
		String username=(String)session.getAttribute("username");
		
		//retrieving attribute from form for update
		String title=request.getParameter("title");
		String description=request.getParameter("description");
		LocalDate targetDate=LocalDate.parse(request.getParameter("targetDate"));
		boolean isDone=Boolean.valueOf(request.getParameter("isDone"));
		
		//updating the todo
		Todo todo=new Todo(id,title,username,description,targetDate,isDone);
		dao.updateTodo(todo);
		
		//calling list function
		listTodo(request,response);
		
	}	
}
