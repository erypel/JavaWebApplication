package com.javawebapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class XRPWebSocketController
{
	@RequestMapping(value = "/xrpWebSocketClient", method = RequestMethod.GET)
	public ModelAndView showRegisterPodcastHome(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("xrpWebSocketClient");
		return mav;
	}
}
