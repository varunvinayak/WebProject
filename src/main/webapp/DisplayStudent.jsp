<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dxc.dao.StudentJdbcDAO"%>
<%@page import="com.dxc.beans.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>STUDENT DETALIS</title>
</head>
<body>

	<%
		session = request.getSession(false);
	if (session != null && session.getAttribute("username") != null) {
		String username = (String) session.getAttribute("username");
	%>
	<h2 align='center'>
		welcome
		<%=username%></h2>

	<%
		try {
		List<Student> students =  new StudentJdbcDAO().findAll();
	%>
	<table border='1' align='center'>
		<tr>
			<th>ID</th>
			<th>NAME</th>
			<th>DOB</th>
			<th>EMAIL</th>
			<th>MOBILE</th>
			<th>EDIT/DELETE</th>
		</tr>
		<%
			for (Student student : students) {
		%>
		<tr>
			<td><%=student.getId()%></td>
			<td><%=student.getName()%></td>
			<%
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			%>
			<td><%=sdf.format(student.getDob())%></td>
			<td><%=student.getEmail()%></td>
			<td><%=student.getMobile()%></td>
			
			<td>
			
			<a href="edit.jsp?id=<%= student.getId() %>">edit</a>
			<a href="delete?id=<%= student.getId() %>" >delete</a>
			</td>
		</tr>
		<%
			}
		} finally {

		}
		%>
		<%
			} 
		%>

	</table>
	<p align="center"><a href="AddStudent.jsp">Add Student</a></p>
	<form action="Logout" method="get">
	   <input type="submit" value="Logout">
	</form>
</body>
</html>