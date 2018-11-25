package com.javawebapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javawebapp.model.User;
import com.javawebapp.service.UserService;
import com.javawebapp.service.WalletService;

@Controller
public class RegistrationController
{
	@Autowired
	public UserService userService;
	
	@Autowired
	public WalletService walletService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", new User());
		return mav;
	}
	
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user)
	{
		userService.register(user);
		walletService.register(user.getId());
		return new ModelAndView("welcome", "userName", user.getUserName());
	}
}