package com.javawebapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javawebapp.model.Podcast;
import com.javawebapp.model.User;

@Controller
public class PodcastController
{
	@RequestMapping(value = "/podcast", method = RequestMethod.GET)
	public ModelAndView showRegisterPodcastHome(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("podcast");
		return mav;
	}
	
	@RequestMapping(value = "/navigateToUploadPodcast", method = RequestMethod.GET)
	public ModelAndView showRegisterUploadPodcast(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("uploadPodcast");
		return mav;
	}
	
	@RequestMapping(value = "/listenToPodcast", method = RequestMethod.GET)
	public ModelAndView showRegisterListenToPodcast(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user, @ModelAttribute("podcast") Podcast podcast)
	{
		ModelAndView mav = new ModelAndView("listenToPodcast");
		mav.addObject("podcast", podcast);
		return mav;
	}
}
