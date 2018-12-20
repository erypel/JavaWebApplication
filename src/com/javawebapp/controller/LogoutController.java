package com.javawebapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javawebapp.dao.impl.UserDaoImpl;
import com.javawebapp.model.Login;

@Controller
public class LogoutController
{
	Logger logger = LogManager.getLogger(LogoutController.class);
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response)
	{
		response.setContentType("text/html");
		Cookie[] cookies = request.getCookies();
		HttpSession session = request.getSession(false);
		
		// Get session's user
		String user = "unknown";
		if(session == null)
		{
			logger.log(Level.WARN, "Session is null when performing logout");
		}
		else
		{
			user = String.valueOf(session.getAttribute("user"));
		}
		
		if(cookies != null)
		{
			for(Cookie c : cookies)
			{
				if(c.getName().equals("JSESSIONID"))
				{
					logger.log(Level.INFO,
							"Logging out user=" + user + " with JSESSIONID=" + c.getValue());
					break;
				}
			}
		}
		
		// invalidate the session if exists
		if(session != null)
		{
			session.invalidate();
		}
		
		logger.log(Level.INFO, "Successfully logged out User=" + user);
		ModelAndView mav = new ModelAndView("/resources/jsp/login.jsp");
		mav.addObject("login", new Login());
		return mav;
	}
}
