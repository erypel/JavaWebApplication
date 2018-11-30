package com.javawebapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RSSFeedController
{
	@RequestMapping(value = "/createRSSFeed", method = RequestMethod.GET)
	public ModelAndView showCreateRssFeed(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("createRSSFeed");
		return mav;
	}
	
	@RequestMapping(value = "/createRSSFeedProcess", method = RequestMethod.POST)
	public ModelAndView processRSSFeed(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("podcast");
		return mav;
	}
}
