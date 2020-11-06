package com.todoapp.conn;

import java.sql.*;
import java.time.LocalDate;

public class connection_db {
	public static Connection con=null;
	   static {
		   try {
			  Class.forName("com.mysql.jdbc.Driver");
			  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","Sidharth3");
		   }
		   catch(Exception ex){
			   ex.printStackTrace();
		   }
		}
		
		public static Connection getcon() {
		 return con;
		}
	   
		public static Date getSQLDate(LocalDate date) {
			return java.sql.Date.valueOf(date);
		}
		
		public static LocalDate getUtilDate(Date sqlDate) { 
			return sqlDate.toLocalDate();
		}
}
