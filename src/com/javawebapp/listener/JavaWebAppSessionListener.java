package com.javawebapp.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javawebapp.db.DBConnectionManager;

@WebListener
public class JavaWebAppSessionListener implements HttpSessionListener
{
	Logger logger = LogManager.getLogger(JavaWebAppSessionListener.class);
	
	public void sessionCreated(HttpSessionEvent sessionEvent)
	{
		logger.info("Session Created::ID=" + sessionEvent.getSession().getId());
	}
	
	public void sessionDestroyed(HttpSessionEvent sessionEvent)
	{
		logger.info("Session Destroyed::ID=" + sessionEvent.getSession().getId());
	}
	
}