<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Welcome</title>
    </head>
    <body>
    	<jsp:include page="header.jsp" />
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
        <table>
            <tr>
                <td>Welcome ${user}. Your session ID is <%=sessionID%></td>
            </tr>
            <tr>
            </tr>
            <tr>
            </tr>
            <tr>
            	<td>Wallet</td>
            	<td>Wallet Id: ${walletId}</td>
            	<td>Public Key: ${publicKey}</td>
            </tr>
        </table>
    </body>
    </html>