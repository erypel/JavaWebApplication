<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload a Podcast!</title>
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
	<h1>Upload your podcast:</h1>
	<form method="post" action="uploadPodcastServlet" enctype="multipart/form-data">
		<table border="0">
			<tr>
				<td>Episode Name: </td>
				<td><input type="text" name="episodeName" size="50"/></td>
			</tr>
			<tr>
				<td>Description: </td>
				<td><input type="text" name="episodeDescription" size="50"/></td>
			</tr>
			<tr>
				<td>Audio File: </td>
				<td><input type="file" name="podcastEpisode" size="50"/></td>
			</tr>
			<tr>
            	<td colspan="2">
            		<input type="submit" value="Save">
                </td>
            </tr>
		</table>
	</form>
</body>
</html>