package com.todoapp.dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	
	import com.todoapp.conn.connection_db;
	import com.todoapp.model.LoginBean;
	
	public class LoginDao {
	
		boolean status=false;
	public boolean loginUser(LoginBean login) {
		
		
	   try {
		   Connection con=connection_db.getcon();
		   PreparedStatement preparedStatement=con.prepareStatement("Select* from users where username=? and password=?");
		   preparedStatement.setString(1, login.getUsername());
		   preparedStatement.setString(2, login.getPassword());
		   
		   ResultSet rs=preparedStatement.executeQuery();
		   status=rs.next();
		   
	   }
	   catch(Exception ex) {
		   ex.printStackTrace();
	   }
	   
	   return status;
	}
	}
