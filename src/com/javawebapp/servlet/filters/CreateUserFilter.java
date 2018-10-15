package com.javawebapp.servlet.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/CreateUserFilter")
public class CreateUserFilter implements Filter{

	private ServletContext context;
	
	public void destroy() {
		// TODO Auto-generated method stub
		//we can close any resources here
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		String URI = req.getRequestURI();
		this.context.log("Requested Resource::" + URI);
		
		// pass the request along the filter
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		this.context.log("CreateUserFilter initialized");
	}

}
