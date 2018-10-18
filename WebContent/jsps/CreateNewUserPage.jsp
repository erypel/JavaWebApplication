<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Create New User</title>
</head>
<body>
<form action="createNewUser" method="post">
    UserName: <input type="text" name="username"/><br>
    Password: <input type="text" name = "password"/><br>
    Confirm Password: <input type="text" name = "verifyPwd"/><br>
    Email: <input type="text" name = "email"/><br>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>