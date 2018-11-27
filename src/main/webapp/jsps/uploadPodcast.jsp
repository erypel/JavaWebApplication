<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload a Podcast!</title>
</head>
<body>
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