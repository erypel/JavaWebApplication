package com.javawebapp.servlet.filters;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.javawebapp.db.ConnectionUtils;
import com.javawebapp.db.DBConnectionManager;
import com.javawebapp.db.MySQLConnectionUtils;

@WebFilter(filterName="jdbcFilter", urlPatterns= {"/*"})
public class JDBCFilter implements Filter{

	public JDBCFilter() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		//Only open connections for the special requests
		//ex. path to the servlet, JSP, ...
		//
		//Avoid open connection for commons request
		//ex. image, css, javascript, ...
		if(this.needJDBC(req))
		{
			System.out.println("Open Connection for: " + req.getServletPath());
			Connection connection = null;
			try
			{
				//Create connection
				connection = MySQLConnectionUtils.getMySQLConnection();
				connection.setAutoCommit(false); //best practice
				
				//Store connection object in attribute of request
				ConnectionUtils.storeConnection(request, connection);
				
				//allow the request to go forward to next filter or target
				chain.doFilter(request, response);
				
				//commit() to complete the transaction with the DB
				connection.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				ConnectionUtils.rollback(connection);
				throw new ServletException();
			}
			finally
			{
				ConnectionUtils.closeConnection(connection);
			}
		}
		//no need to open connection
		else
		{
			//allow the request to go forward to the next filter or target
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean needJDBC(HttpServletRequest request)
	{
		System.out.println("Checking if the JDBC Filter is needed.");
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		String urlPattern = servletPath;
		
		if(pathInfo != null)
		{
			urlPattern = servletPath + "/*";
		}
		
		//Key: servletName
		//Value: ServletRegistration
		Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext().getServletRegistrations();
	
		//Collection of all servlets in the webapp
		Collection<? extends ServletRegistration> values = servletRegistrations.values();
		for(ServletRegistration sr : values)
		{
			Collection<String> mappings = sr.getMappings();
			if(mappings.contains(urlPattern))
				return true;
		}
		return false;
	}

}
