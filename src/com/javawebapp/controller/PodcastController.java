package com.javawebapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javawebapp.model.User;

@Controller
public class PodcastController
{
	@RequestMapping(value = "/podcast", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user)
	{
		ModelAndView mav = new ModelAndView("podcast");
		mav.addObject("userName", user.getUserName());
		return mav;
	}
}
