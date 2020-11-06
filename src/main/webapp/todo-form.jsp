<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
 integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
 crossorigin="anonymous">

</head>
<body>
   <header>
	<nav class="navbar navbar-expand-md navbar-dark"
   style="background-color: tomato">
   <div>
     Todo App
   </div>
   
    <ul class="navbar-nav">
    <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Todos</a></li>
   </ul>
	
	 <ul class="navbar-nav navbar-collapse justify-content-end">
     <li><a href="<%=request.getContextPath()%>/login" class="nav-link">Logout</a></li>
     </ul>
	</nav>
	</header>
	
	<div class="container col-md-5">
	<div class="card">
     <div class="card-body">
	   
	<c:if test="${todo!=null}">
        <c:set var="action" value="update"/>
	</c:if> 
	
	<c:if test="${todo==null}">
	     <c:set var="action" value="insert"/>
	</c:if>
	
	<form action="<%=request.getContextPath()%>/${action}" method="post">
	<caption>
	<h2>
	
	  <c:if test="${todo!=null}">
	     Edit Todo
	  </c:if>
	     
	   <c:if test="${todo == null}">
	      Add New Todo
	   </c:if>
	   
	 </h2>
	 
	</caption>
   
    <c:if test="${todo!=null}">
    <input type="hidden" name="id" value="<c:out value="${todo.getId()}" />"/>
    </c:if>
    
    <fieldset class="form-group">
    <label>Todo Title</label>
    <input type="text" value="<c:out value="${todo.getTitle()}" />" class="form-control" name="title" required="required" minlength="5">
    </fieldset>
    
    <fieldset class="form-group">
    <label>Todo Description</label>
    <input type="text" value="<c:out value="${todo.getDescription()}"/>" class="form-control" name="description" minlength="5">
    </fieldset>
    
    <fieldset class="form-group">
    <label>Todo Status</label>
    <select class="form-control" name="isDone">
    <option value="false">In Progress</option>
    <option value="true">Complete</option>
    </select>
    </fieldset>
    
    <fieldset class="form-group">
    <label>Todo target date</label>
    <input type="date" value="<c:out value="${todo.getTargetdate()}"/>" class="form-control" name="targetDate" required="required">
    </fieldset>
    
   <button type="submit" class="btn btn-success">Save</button>
   </form>
   </div>
   </div>
   </div>
   
</body>
</html>