package com.javawebapp.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javawebapp.objects.User;

public class ConnectionUtils {
	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME";
	
	public static Connection getMyConnection() throws SQLException, ClassNotFoundException {
		return MySQLConnectionUtils.getMySQLConnection();
	}
	
	/**
	 * Store connection in request attribute
	 * (Information stored only exists during requests)
	 * @param request
	 * @param connection
	 */
	public static void storeConnection(ServletRequest request, Connection connection) {
		request.setAttribute(ATT_NAME_CONNECTION, connection);
	}
	
	// Get the Connection object stored in the attribute of the request
	public static Connection getStoredConnection(ServletRequest request)
	{
		Connection connection = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
		return connection;
	}
	
	//Store user info in Session
	public static void storeLoggedInUser(HttpSession session, User loggedInUser)
	{
		//only the JSP can access via ${loggedInUser}
		session.setAttribute("loggedInUser", loggedInUser);
	}
	
	public static User getLoggedInUser(HttpSession session)
	{
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		return loggedInUser;
	}
	
	//Store info in Cookie
	public static void storeUserCookie(HttpServletResponse response, User user)
	{
		Cookie cookie = new Cookie(ATT_NAME_USER_NAME, user.getUserName());
		cookie.setMaxAge(24*60*60); //number of seconds in a day
		response.addCookie(cookie);
	}
	
	public static String getUserNameInCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		{
			for(Cookie c : cookies)
			{
				if(ATT_NAME_USER_NAME.equals(c.getName()))
				{
					return c.getValue();
				}
			}
		}
		return null;
	}
	
	// delete cookie
	public static void deleteUserCookie(HttpServletResponse response)
	{
		Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
		// cookie will expire immediately
		cookieUserName.setMaxAge(0);
		response.addCookie(cookieUserName);
	}
	
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection connection) {
		try {
			connection.rollback();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Test Connection
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println("Get connection...");
		
		//get a connection object
		Connection connection = ConnectionUtils.getMyConnection();
		
		System.out.println("Got connection " + connection);
		System.out.println("Done");
	}
}
