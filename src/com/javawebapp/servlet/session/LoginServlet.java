package com.javawebapp.servlet.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Tutorial - Servlet Example
 */
@WebServlet(description = "Login Servlet", urlPatterns = { "/LoginServlet" }, initParams = {
		@WebInitParam(name = "user", value = "admin"), @WebInitParam(name = "password", value = "password") })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String userID = "admin";
	private final String password = "password";

	public void init() throws ServletException {
		// we can create DB connection resource here and set it to Servlet context
		if (getServletContext().getInitParameter("dbURL").equals("jdbc:mysql://localhost/mysql_db")
				&& getServletContext().getInitParameter("dbUser").equals("mysql_user")
				&& getServletContext().getInitParameter("dbUserPwd").equals("mysql_pwd"))
			getServletContext().setAttribute("DB_Success", "True");
		else
			throw new ServletException("DB Connection error");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get request parameters for userID and password
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");

		// get servlet config init params
		String userID = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");
		// logging example
		log("User=" + user + "::password=" + pwd);

		if (userID.equals(user) && password.equals(pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			//setting session to expire in 30 minutes
			session.setMaxInactiveInterval(30*60);
			Cookie userName = new Cookie("user", user);
			//userName.setMaxAge(30*60);
			response.addCookie(userName);
			String encodedURL = response.encodeRedirectURL("jsps/LoginSuccess.jsp");
			
			response.sendRedirect(encodedURL);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
		rd.include(request, response);
	}
}
