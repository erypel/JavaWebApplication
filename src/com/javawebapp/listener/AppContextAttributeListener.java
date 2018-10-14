package com.javawebapp.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextAttributeListener implements ServletContextAttributeListener {

	@Override
	public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
		servletContextAttributeEvent.getServletContext().log("ServletContext attribute added::{"
				+ servletContextAttributeEvent.getName() + ", " + servletContextAttributeEvent.getValue() + "}");
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
		servletContextAttributeEvent.getServletContext().log("ServletContext attribute removed::{"
				+ servletContextAttributeEvent.getName() + ", " + servletContextAttributeEvent.getValue() + "}");
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
		servletContextAttributeEvent.getServletContext().log("ServletContext attribute replaced::{"
				+ servletContextAttributeEvent.getName() + ", " + servletContextAttributeEvent.getValue() + "}");
	}

}