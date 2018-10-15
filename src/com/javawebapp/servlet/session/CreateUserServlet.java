package com.javawebapp.servlet.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description = "Create User Servlet", urlPatterns = { "/createNewUser" })
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		/*// we can create DB connection resource here and set it to Servlet context
		if (getServletContext().getInitParameter("dbURL").equals("jdbc:mysql://localhost/mysql_db")
				&& getServletContext().getInitParameter("dbUser").equals("mysql_user")
				&& getServletContext().getInitParameter("dbUserPwd").equals("mysql_pwd"))
			getServletContext().setAttribute("DB_Success", "True");
		else
			throw new ServletException("DB Connection error");
			
			  <input type="text" name="username">
    <input type="text" name = "password">
    <input type="text" name = "verifyPwd">
    <input type="text" name = "email">
			*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get request parameters for new user creation
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		String verify = request.getParameter("verifyPwd");
		String email = request.getParameter("email");

		PrintWriter out = response.getWriter();
		//TODO this will need more work
		// check that parameters are valid
		if(username == null || username.equals(""))
			out.println("<font color=red>Please enter a username.</font>");
		else if(pwd == null || pwd.equals(""))
			out.println("<font color=red>Please enter a password.</font>");
		else if(verify == null || verify.equals(""))
			out.println("<font color=red>Please confirm password.</font>");
		else if(email == null || email.equals(""))
			out.println("<font color=red>Please enter an email.</font>");
		// everything is good, send user to login page
		else if (pwd.equals(verify)) {
			// logging the new user's details
			log("Creating User=" + username + "::password=" + pwd +"::email=" + email);
			String encodedURL = response.encodeRedirectURL("/login.html");
			response.sendRedirect(encodedURL);
		} else {
			out.println("<font color=red>Something's up...</font>");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("jsps/CreateNewUserPage.jsp");
		rd.include(request, response);
	}
}
