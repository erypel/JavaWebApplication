<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Success Page</title>
</head>
<body>
	<%
		//only allow access if session exists
		String user = (String) session.getAttribute("user");
		long userId = Long.valueOf((String) session.getAttribute("userId"));
		String userName = null;
		String sessionID = null;
		String privateKey = null;
		String publicKey = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		{
			for(Cookie cookie : cookies)
			{
				if(cookie.getName().equals("user"))
					userName = cookie.getValue();
				if(cookie.getName().equals("userId"))
					userId = Long.valueOf(cookie.getValue());
				if(cookie.getName().equals("JSESSIONID"))
					sessionID = cookie.getValue();
			}
		}
		else
		{
			sessionID = session.getId();
		}
	%>
	<h2>
		Hi
		<%=userName%>, Login successful. Your Session ID=<%=sessionID%>
	</h2>
	<!-- for testing -->
	<h3>
		Your user id is <%=userId%>.
	</h3>
	<!-- for testing. definitely do not want private key here -->
	<h3>
		Your wallet's public key is <%=publicKey%> and your private key is <%=privateKey%>
	</h3>
	<br>
	<a href="CheckoutPage.jsp">Logout Page</a>
	<form action="/WalletController/createWallet/${userId}" method="post">
		<input type="submit" value="Create New Wallet"/>
	</form>
	<form action="${pageContext.request.contextPath}/LogoutServlet"
		method="post">
		<input type="submit" value="Logout"/>
	</form>
</body>
</html>
