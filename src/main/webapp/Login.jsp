<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<form action="loginValidate" method="post">
	User Name : <input type="text" name="username"><br>
	Password : <input type="password" name="password"><br>
	<input type="submit" value="Login">
	<input type="reset" value="Cancel">
	</form>
</body>
</html>