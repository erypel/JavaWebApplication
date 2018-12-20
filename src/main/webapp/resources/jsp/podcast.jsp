<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.javawebapp.dao.impl.PodcastDaoImpl" %>
<%@ page import="com.javawebapp.model.Podcast" %>
<%@ page import="com.javawebapp.model.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Podcasts</title>
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
	<h2>Podcasts</h2>
	<table border="1">
		<tr>
			<th>Episode</th>
			<th>Description</th>
			<th>Path</th> <!-- turn the episode name into a link to the audio or add audio player here -->
		</tr>
		<%
			PodcastDaoImpl podcastDao = new PodcastDaoImpl();
			List<Podcast> podcasts = podcastDao.get50Podcasts();
		
			for(Podcast p : podcasts)
			{
		%>
		<tr>
			<td> <a href="listenToPodcast.action?id=<%=String.valueOf(p.getID())%>"><%= p.getEpisodeName() %></a></td>
			<td> <%= p.getDescription() %> </td>
			<td> <%= p.getPath() %> </td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>