package com.javawebapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javawebapp.model.Login;



@Controller
public class LogoutController
{
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response)
	{
		response.setContentType("text/html");
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		{
			for(Cookie c : cookies)
			{
				if(c.getName().equals("JSESSIONID"))
				{
					//TODO log("JSESSIONID=" + c.getValue());
					break;
				}
			}
		}
		
		// invalidate the session if exists
		HttpSession session = request.getSession(false);
		//TODO logger.info("Logging out User=" + session.getAttribute("user"));
		if(session != null)
		{
			session.invalidate();
		}
		
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new Login());
		return mav;
	}
}


