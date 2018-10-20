package com.javawebapp.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.javawebapp.db.DBConnectionManager;

@WebListener
public class AppContextListener implements ServletContextListener
{
	
	public void contextDestroyed(ServletContextEvent servletContextEvent)
	{
		ServletContext context = servletContextEvent.getServletContext();
		DBConnectionManager dbManager = (DBConnectionManager) context.getAttribute("DBManager");
		dbManager.closeConnection();
		context.log("Database connection closed for Application");
	}
	
	public void contextInitialized(ServletContextEvent servletContextEvent)
	{
		ServletContext context = servletContextEvent.getServletContext();
		
		String url = context.getInitParameter("dbURL");
		String username = context.getInitParameter("dbUser");
		String password = context.getInitParameter("dbUserPwd");
		
		// create a db connection from init parameters and set it to context
		DBConnectionManager dbManager = new DBConnectionManager(url, username, password);
		context.setAttribute("DBManager", dbManager);
		context.log("Database connection initialized for Application.");
	}
	
}