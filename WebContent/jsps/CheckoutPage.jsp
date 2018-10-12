<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Checkout Page</title>
</head>
<body>
<%
	//allow access only if a session exists
	if(session.getAttribute("user") == null)
	{
		response.sendRedirect("login.html");
	}
	String userName = null;
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
	if(cookies != null)
	{
		for(Cookie c : cookies)
		{
			if(c.getName().equals("user"))
				userName = c.getValue();
		}
	}
%>
<h3>Hi <%=userName %>, do the checkout.</h3>
<br>
<form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>