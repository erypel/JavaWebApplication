package com.javawebapp.service;

import org.springframework.stereotype.Component;

import com.javawebapp.dao.impl.RSSFeedDaoImpl;
import com.javawebapp.dao.impl.RSSFeedMessageDaoImpl;
import com.javawebapp.dao.impl.UserDaoImpl;
import com.javawebapp.model.Podcast;
import com.javawebapp.model.RSSFeed;
import com.javawebapp.model.RSSFeedMessage;
import com.javawebapp.model.User;

@Component
public class RSSFeedDataService
{
	RSSFeedDaoImpl rssFeedDao = new RSSFeedDaoImpl();
	RSSFeedMessageDaoImpl rssFeedMessageDao = new RSSFeedMessageDaoImpl();
	UserDaoImpl userDao = new UserDaoImpl();
	
	public boolean insertRssFeed(RSSFeed feed)
	{
		return rssFeedDao.insertRSSFeed(feed);
	}
	
	public boolean createRSSFeedMessage(Podcast podcast) throws Exception
	{
		RSSFeed feed = rssFeedDao.getRSSFeed(podcast.getOwnerId());
		if(feed == null)
			throw new Exception("You need an RSS Feed.");
		User user = userDao.getUser(podcast.getOwnerId()); // TODO could I use HttpSession stuff here from the calling
															// class?
		RSSFeedMessage message = new RSSFeedMessage(podcast.getEpisodeName(), podcast.getDescription(),
				podcast.getPath(), user.getUserName(), feed.getId(), podcast.getID());
		return rssFeedMessageDao.insertRSSFeedMessage(message);
	}
	
	public boolean deleteRSSFeedMessage(Podcast podcast)
	{
		RSSFeedMessage message = getRSSFeedMessage(podcast.getID());
		return rssFeedMessageDao.deleteRSSFeedMessage(message);
	}
	
	public RSSFeedMessage getRSSFeedMessage(long podcastId)
	{
		return rssFeedMessageDao.getRSSFeedMessage(podcastId);
	}
}
