<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.javawebapp.dao.impl.PodcastDaoImpl" %>
<%@ page import="com.javawebapp.model.Podcast" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Podcasts</title>
</head>
<body>
	<h2>Content goes here</h2>
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
				<td> <%= p.getEpisodeName() %> </td>
				<td> <%= p.getDescription() %> </td>
				<td> <%= p.getPath() %> </td>
			</tr>
	<%
		}
		
	%>
	
	</table>
	<audio controls>
    	<source src="http://localhost:8080/JavaWebApp/uploads/testWav.wav" type="audio/wav">
	</audio>
	<br>
	<a href="navigateToUploadPodcast.action">Upload</a>
</body>
</html>