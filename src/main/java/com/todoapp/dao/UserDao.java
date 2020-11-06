package com.todoapp.dao;
import java.sql.*;

import com.todoapp.model.*;
import com.todoapp.conn.connection_db;

public class UserDao {

	public int registeremployee(User user) {
	   String query="Insert into users"+"(first_name,last_name,username,password) values"+"(?,?,?,?)";
		//String query="Insert into users (first_name,last_name,username,password) values ('abcde','132424','asdasf','fasfasf')";
	   try {
		   
		   System.out.println(user.getFirstName());
		   System.out.println(user.getLastName());
		   System.out.println(user.getUsername());
		   System.out.println(user.getPassword());
		   
		   Connection con=connection_db.getcon();
		   PreparedStatement preparedStatement=con.prepareStatement(query);
		   
		   preparedStatement.setString(1,user.getFirstName());
		   preparedStatement.setString(2,user.getLastName());
	       preparedStatement.setString(3,user.getUsername());
		   preparedStatement.setString(4,user.getPassword());
		   
		   preparedStatement.executeUpdate();
		   
		   preparedStatement.close();
		   con.close();
		   
	   }
	   catch(Exception ex) {
		   System.out.println("connection unsuccessful");
		   return 0;
	   }
			return 1;
	}

}
