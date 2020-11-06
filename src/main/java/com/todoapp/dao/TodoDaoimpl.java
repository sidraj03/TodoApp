package com.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.todoapp.conn.connection_db;
import com.todoapp.model.Todo;

public class TodoDaoimpl implements TodoDao{
   private static final String insert_todos="insert into todos "+"(description,is_done,target_date,username,title) values"
                                             +"(?,?,?,?,?)";
   private static final String select_todos="select* from todos where id =?";
   private static final String select_all_todos="select*from todos where username=?";
   private static final String delete_todos="delete from todos where id=?";
   private static final String update_todos="update todos set title = ?, username= ?, description =?, target_date =?, is_done = ? where id = ?";
   
   
	
	@Override
	public void insertTodo(Todo todo) throws SQLException {
      try {
    	  Connection con=connection_db.getcon();
    	  
    	  PreparedStatement preparedstatement=con.prepareStatement(insert_todos);
    	  preparedstatement.setString(1, todo.getDescription());
    	  preparedstatement.setBoolean(2, todo.isStatus());
    	  preparedstatement.setDate(3, connection_db.getSQLDate(todo.getTargetdate()));
    	  preparedstatement.setString(4, todo.getUsername());
    	  preparedstatement.setString(5, todo.getTitle());
    	  
    	  preparedstatement.executeUpdate();
      }
      catch(Exception ex) {
    	  ex.printStackTrace();
      }
	}

	@Override
	public Todo selectTodo(long todoId) {
		Todo todo=null;
		try {
			Connection con=connection_db.getcon();
			PreparedStatement preparedstatement=con.prepareStatement(select_todos);
			preparedstatement.setLong(1,todoId);
			
			ResultSet rs=preparedstatement.executeQuery();
			
			while(rs.next()) {
				long id=rs.getLong("id");
				String title=rs.getString("title");
				String username=rs.getString("username");
				String description=rs.getString("description");
				LocalDate date=rs.getDate("target_date").toLocalDate();
				boolean isDone=rs.getBoolean("is_done");
				todo=new Todo(id,title,username,description,date,isDone);
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return todo;
	}

	@Override
	public List<Todo> selectAllTodos(String loginName) {
		
       List <Todo> todos=new ArrayList<>();
      try {
       Connection con=connection_db.getcon();
       PreparedStatement preparedstatement=con.prepareStatement(select_all_todos);
	   preparedstatement.setString(1,loginName);
       
       ResultSet rs=preparedstatement.executeQuery();
	
       while(rs.next()) {
    	   long id=rs.getLong("id");
    	   String title=rs.getString("title");
    	   String username=rs.getString("username");
    	   String description=rs.getString("description");
    	   LocalDate date=rs.getDate("target_date").toLocalDate();
    	   boolean isDone=rs.getBoolean("is_Done");
    	   todos.add(new Todo(id,title,username,description,date,isDone));
	}
	}
	catch(Exception ex) {
		ex.printStackTrace();
	}
		
		return todos;
	}

	@Override
	public boolean deleteTodo(int id) throws SQLException {
		boolean delStatus = false;
		try {
			Connection con=connection_db.getcon();
			PreparedStatement preparedStatement=con.prepareStatement(delete_todos);
			preparedStatement.setInt(1, id);
			
			delStatus=preparedStatement.executeUpdate()>0;
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return delStatus;
	}

	@Override
	public boolean updateTodo(Todo todo) throws SQLException {
		boolean update=false;
		
		try {
			Connection con=connection_db.getcon();
			PreparedStatement preparedstatement=con.prepareStatement(update_todos);
			
			preparedstatement.setString(1,todo.getTitle());
			preparedstatement.setString(2,todo.getUsername());
			preparedstatement.setString(3,todo.getDescription());
			preparedstatement.setDate(4,connection_db.getSQLDate(todo.getTargetdate()));
			preparedstatement.setBoolean(5,todo.isStatus());
			preparedstatement.setLong(6,todo.getId());
			
			update=preparedstatement.executeUpdate()>0;
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return update;
	}

}
