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
		Podcast podcast = (Podcast) request.getAttribute("podcast");
	%>
	<audio controls>
    	<source src=<%=podcast.getPath() %> type="audio/wav">
	</audio>
	<br>
	<a href="podcast.action">Return to list</a>
</body>
</html>