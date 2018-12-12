package com.javawebapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javawebapp.model.RSSFeed;
import com.javawebapp.service.RSSFeedDataService;
import com.javawebapp.service.RSSFeedWriterService;

@Controller
public class RSSFeedController
{
	@Autowired
	RSSFeedDataService rssFeedDataService;
	
	@RequestMapping(value = "/createRSSFeed", method = RequestMethod.GET)
	public ModelAndView showCreateRssFeed(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("createRSSFeed");
		return mav;
	}
	
	@RequestMapping(value = "/createRSSFeedProcess", method = RequestMethod.POST)
	public ModelAndView processRSSFeed(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		// Get the field values
		String title = request.getParameter("title");
		String link = request.getParameter("link"); // TODO remove from front end and dynamically create based off of
													// user profile
		String description = request.getParameter("description");
		String language = request.getParameter("language");
		String copyright = request.getParameter("copyright");
		long ownerId = Long.parseLong(session.getAttribute("userId").toString());
		
		// TODO this is a terrible way to do Date at scale
		Date publishDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String dateForDB = sdf.format(publishDate);
		
		RSSFeed feed = new RSSFeed(title, link, description, language, copyright, dateForDB, ownerId);
		
		String message = "";
		if(rssFeedDataService.insertRssFeed(feed))
			message = "Successfully stored RSS Feed";
		else
			message = "Failed to store RSS Feed";
		
		// Write the RSS feed's xml. Throws exception, maybe //TODO handle exception
		String filePath = rssFeedDataService.buildFilePath(feed);
		RSSFeedWriterService rssWriter = new RSSFeedWriterService(feed, filePath);
		rssWriter.write();
		
		//TODO a different view should go here
		ModelAndView mav = new ModelAndView("Message");
		mav.addObject("message", message);
		return mav;
	}
}
