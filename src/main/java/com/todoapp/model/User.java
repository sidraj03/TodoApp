package com.todoapp.model;

import java.io.Serializable;

import com.todoapp.dao.*;

public class User implements Serializable {
  private String firstName;
  private String LastName;
  private String username;
  private String password;
  
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
 
//	public static void main(String[]args){
//		User u=new User();
//		u.setFirstName("sid");
//		u.setLastName("miglani");
//		u.setPassword("1234");
//		u.setUsername("hey");
//		
//		UserDao obj=new UserDao();
//		obj.registeremployee(u);
//		
//	}
}
