package com.javawebapp.service;

import org.springframework.stereotype.Component;

import com.javawebapp.dao.impl.RSSFeedDaoImpl;
import com.javawebapp.model.RSSFeed;

@Component
public class RSSFeedDataService
{
	RSSFeedDaoImpl rssFeedDao = new RSSFeedDaoImpl();
	
	public boolean insertRssFeed(RSSFeed feed)
	{
		return rssFeedDao.insertRSSFeed(feed);
	}
}
