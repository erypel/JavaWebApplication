package com.javawebapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description = "Logout Servlet", urlPatterns = { "/LogoutServlet" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 *
	 *	When logging out, we will want to remove the cookie from the client's 
	 *	browser.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		Cookie[] cookies = request.getCookies();
		if(cookies!=null)
		{
			for(Cookie c : cookies)
			{
				if(c.getName().equals("JSESSIONID"))
				{
					System.out.println("JSESSIONID=" + c.getValue());
				}
				// can't delete cookie, so have it time out immediately
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		
		//invalidate the session if exists
		HttpSession session = request.getSession(false);
		System.out.println("User=" + session.getAttribute("user"));
		if(session != null)
		{
			session.invalidate();
		}
		
		response.sendRedirect("login.html");
	}

}