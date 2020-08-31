<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dxc.beans.Student"%>
<%@page import="com.dxc.dao.StudentJdbcDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false"%>
<%
	if (request.getMethod().equalsIgnoreCase("get")) {
%>
<%! 	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit</title>
</head>
<body>

<%  
StudentJdbcDAO student = new StudentJdbcDAO();
String id=request.getParameter("id");  
Student std = student.find(Integer.parseInt(id));  
if(session!=null && session.getAttribute("un")!=null) {
%>
	<h1>Edit Student with id : <%=std.getId() %></h1> 
		<form action = "update" method = "post">
			<table>
					<tr><td><input type="hidden" name="id" value="<%=std.getId() %>"/></td></tr>
					<tr><td>Name:</td><td><input type = "text" name="name" value="<%=std.getName()%>"/></td></tr>
					<tr><td>Date of birth:</td><td><input type = "text" name="dob" value="<%=sdf.format(std.getDob())%>"/></td></tr>
					<tr><td>Email:</td><td><input type = "text" name="email" value="<%=std.getEmail()%>"/></td></tr>
					<tr><td>Mobile:</td><td><input type = "text" name="mobile" value="<%=std.getMobile()%>"/></td></tr>
					<tr><td><input type="submit" value="Edit Student"/></td></tr>
			</table>
		
		</form>
		<%	}
		else {
			response.sendRedirect("login");
		}%>
<a href=logout>Log Out</a>
</body>
</html>
<% }else if(request.getMethod().equalsIgnoreCase("post")){
		response.sendRedirect("Login.jsp");
	}
%>