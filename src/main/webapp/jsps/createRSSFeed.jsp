<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Your RSS Feed</title>
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
	<h1>Create your RSS Feed</h1>
	 <form id="rssFeedForm" action="createRSSFeedProcess.action" method="post" enctype="multipart/form-data">
                <table>
                    <tr>
                    	<td>Title: </td>
                    	<td><input type="text" name = "title" size="50"/></td>
                    </tr>
                    <tr>
                    	<td>Link: </td>
                    	<td><input type="text" name = "link" size="50"/></td>
                    </tr>
                    <tr>
                    	<td>Language: </td>
                    	<td><input type="text" name = "language" size="50"/></td>
                    </tr>
                    <tr>
                    	<td>Description: </td>
                    	<td><input type="text" name = "description" size="50"/></td>
                    </tr>
                    <tr>
						<td>Copyright: </td>
						<td><input type="text" name="copyright" size="50"/></td>
					</tr>
                    <tr>
                        <td></td>
                        <td colspan="2">
            				<input type="submit" value="Create">
               			 </td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td></td>
                        <td><a href="home.action">Home</a>
                        </td>
                    </tr>
                </table>
            </form>
</body>
</html>