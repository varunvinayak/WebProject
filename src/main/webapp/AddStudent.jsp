<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> New Student</title>
</head>
<body>
<h2 align="center">STUDENT FORM</h2><br>
<form action="AddStudent" method="post">
ID:<input type="text" name="id"><br>
NAME:<input type="text" name="name"><br>
DOB:<input type="text" name="dob"><br>
EMAIL:<input type="text" name="email"><br>
MOBILE:<input type="text" name="mobile"><br>
<input type="submit" value="add">
<input type="reset" value="clear">
</form>

</body>
</html>