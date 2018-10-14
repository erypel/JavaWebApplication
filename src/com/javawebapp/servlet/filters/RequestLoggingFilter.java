package com.javawebapp.servlet.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class RequestLoggingFilter
 * @author Evan
 *
 */
@WebFilter("/RequestLoggingFilter")
public class RequestLoggingFilter implements Filter{
	private ServletContext context;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		//we can close resourses here
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Enumeration<String> params = req.getParameterNames();
		while(params.hasMoreElements()) {
			String name = params.nextElement();
			String value = request.getParameter(name);
			this.context.log(req.getRemoteAddr() + "::Request Params::{" + name + "=" + value +"}");
		}
		
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies)
			{
				this.context.log(req.getRemoteAddr() + "::Cookies::{" + c.getName() + "," + c.getValue() + "}");
			}
		}
		//pass the request along the filter chain
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		this.context.log("RequestLoggingFilter initialized");
	}
}
