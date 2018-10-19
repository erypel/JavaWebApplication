package com.javawebapp.servlet.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.javawebapp.dao.UserDao;
import com.javawebapp.dao.impl.UserDaoImpl;
import com.javawebapp.db.ConnectionUtils;
import com.javawebapp.db.MySQLConnectionUtils;
import com.javawebapp.objects.User;

@WebFilter(filterName="cookieFilter", urlPatterns= {"/*"})
public class CookieFilter implements Filter{
	public static final String COOKIE_CHECKED = "COOKIE_CHECKED";
	public static final String CHECKED = "CHECKED";

	public CookieFilter() {}
	
	/**
	 * Check the Cookie information stored by the browser to see
	 * if the user has logged in recently. If so, automatically
	 * log in
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		User userInSession = ConnectionUtils.getLoggedInUser(session);
		
		if(userInSession != null)
		{
			session.setAttribute(COOKIE_CHECKED, CHECKED);
			chain.doFilter(request, response);
			return;
		}
		
		//Connection was created in JDBCFilter
		Connection connection = ConnectionUtils.getStoredConnection(request);
		
		//Flag check cookie
		String isChecked = (String) session.getAttribute(COOKIE_CHECKED);
		if(isChecked == null && connection != null)
		{
			String userName = ConnectionUtils.getUserNameInCookie(req);
			
			UserDao userDao = new UserDaoImpl();
			//want to change following line to use connection also
			User user = userDao.getUser(userName);
			ConnectionUtils.storeLoggedInUser(session, user);
			
			//Mark Cookies checked
			session.setAttribute(COOKIE_CHECKED, CHECKED);
		}
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	

}
