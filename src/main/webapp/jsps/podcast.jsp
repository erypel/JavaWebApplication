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
						<td> <a href="listenToPodcast.action"><%= p.getEpisodeName() %></a></td>
						<td> <%= p.getDescription() %> </td>
						<td> <%= p.getPath() %> </td>
					</tr>
			<%
				}
		
			%>
	</table>
	<br>
	<%
		User user = (User) request.getAttribute("user");
	%>
	<a href="navigateToUploadPodcast.action">Upload</a>
	<a href="home.action">Home</a>
</body>
</html>