package com.javawebapp.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class JavaWebAppServletRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
		ServletRequest servletRequest = servletRequestEvent.getServletRequest();
		System.out.println("ServletRequest destroyed. Remote IP=" + servletRequest.getRemoteAddr());
	}

	@Override
	public void requestInitialized(ServletRequestEvent servletRequestEvent) {
		ServletRequest servletRequest = servletRequestEvent.getServletRequest();
		System.out.println("ServletRequest initialized. Remote IP=" + servletRequest.getRemoteAddr());
	}

}