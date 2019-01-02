package com.javawebapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javawebapp.model.LocalXRPLedger;
import com.javawebapp.model.User;
import com.javawebapp.service.UserService;
import com.javawebapp.service.XRPWalletService;

@Controller
public class RegistrationController
{
	@Autowired
	public UserService userService;
	
	@Autowired
	public XRPWalletService walletService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("/resources/jsp/register.jsp");
		mav.addObject("user", new User());
		return mav;
	}
	
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user)
	{
		userService.register(user);
		LocalXRPLedger wallet = walletService.register(user.getId());
		
		ModelAndView mav = new ModelAndView("/resources/jsp/welcome.jsp", "userName", user.getUserName());
		mav.addObject("firstname", user.getUserName());
		mav.addObject("walletId", wallet.getWalletId());
		mav.addObject("balance", wallet.getFunds());
		mav.addObject("userId", user.getId());
		
		//Add a cookie
		HttpSession session = request.getSession();
		session.setAttribute("user", user.getUserName());
		session.setAttribute("userId", String.valueOf(user.getId()));
		// setting session to expire in 30 minutes
		session.setMaxInactiveInterval(30 * 60);
		Cookie cookie = new Cookie("user", user.getUserName());
		response.addCookie(cookie);
		
		return mav;
	}
}