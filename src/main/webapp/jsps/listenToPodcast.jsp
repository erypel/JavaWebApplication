<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.javawebapp.model.Podcast" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listen</title>
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
	<h1>path is ${request.contextPath} </h1>
	<a href="./src/main/webapp/WEB-INF/lib/tomcat7/webapps/">Folder</a>
	<!-- need to use Tomcat here. theres somewhere where you can store files. test with Chrome-->
	<audio controls>
    	<source src="/JavaWebApplication/WebContent/uploads/testWav.wav" type="audio/wav">
	</audio>
	<embed src="/JavaWebApplication/WebContent/uploads/testWav.wav" showcontrols="true" width="425" height="350"></embed>
	<br>
	<div id="waveform"></div>
	<script src="https://unpkg.com/wavesurfer.js"></script>
		
		<!-- Move below script to its own file -->
		<script type = "text/javascript">
			//create the player
			var wavesurfer = WaveSurfer.create({
		    	container: '#waveform',
		        scrollParent: true
			});
			
			//load the audio file
			wavesurfer.load('\\JavaWebApplication\\uploads\\testWav.wav');
			
			//when ready, play
			wavesurfer.on('ready', function () {
			    wavesurfer.play();
			});
		</script>
		
	<a href="podcast.action">Return to list</a>
</body>
</html>