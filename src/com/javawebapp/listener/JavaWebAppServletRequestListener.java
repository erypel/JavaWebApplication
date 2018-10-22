package com.javawebapp.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javawebapp.db.DBConnectionManager;

@WebListener
public class JavaWebAppServletRequestListener implements ServletRequestListener
{
	Logger logger = LogManager.getLogger(JavaWebAppServletRequestListener.class);
	public void requestDestroyed(ServletRequestEvent servletRequestEvent)
	{
		ServletRequest servletRequest = servletRequestEvent.getServletRequest();
		logger.info("ServletRequest destroyed. Remote IP=" + servletRequest.getRemoteAddr());
	}
	
	public void requestInitialized(ServletRequestEvent servletRequestEvent)
	{
		ServletRequest servletRequest = servletRequestEvent.getServletRequest();
		logger.info("ServletRequest initialized. Remote IP=" + servletRequest.getRemoteAddr());
	}
	
}