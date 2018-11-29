<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.javawebapp.model.Podcast" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
		<h1>Welcome ${user}. Your session ID is <%=sessionID%></h1>

	<h1>path is ${path} </h1>
	<!-- need to use Tomcat here. theres somewhere where you can store files. test with Chrome -->
	<audio controls>
    	<source src="http://localhost:8080/JavaWebApplication/uploads/testWav.wav" type="audio/wav">
	</audio>
	<br>
	<a href="podcast.action">Return to list</a>
</body>
</html>