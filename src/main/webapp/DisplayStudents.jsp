<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dxc.dao.StudentJdbcDAO"%>
<%@page import="com.dxc.beans.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false"%>
<%
	if (request.getMethod().equalsIgnoreCase("post")) {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%! 	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); %>
<%
		if(session!=null && session.getAttribute("un")!=null) {

			String username = (String)session.getAttribute("un");
			out.println("<h2>Welcome to "+username+" </h2>");
			
				List<Student> students = new StudentJdbcDAO().findAll();
				request.setAttribute("students", students);
%>
				<table>
				<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Date of Birth</th>
				<th>Email</th>
				<th>Mobile</th>
				</tr>
				<c:forEach items="${students}" var="u"> 
					<tr>
					<td>${u.getId()}</td>
					<td>${u.getName()}</td>
					<td>${sdf.format(u.getDob())}</td>
					<td>${u.getEmail()}</td>
					<td>${u.getMobile()}</td>
					<td><a href="Edit.jsp?id=${u.getId()}">Edit</a></td>  
					<td><a href="delete?id=${u.getId()}">Delete</a></td>
					</tr>
				</c:forEach> 
				</table>
		<%	}
		else {
			response.sendRedirect("login");
		}%>
<a href=logout>Log Out</a>
</body>
</html>

<% }else if(request.getMethod().equalsIgnoreCase("get")){
		response.sendRedirect("Login.jsp");
	}
%>