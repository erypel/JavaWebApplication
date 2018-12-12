package com.javawebapp.dao;

import com.javawebapp.model.RSSFeed;
import com.javawebapp.model.RSSFeedMessage;

public interface RSSFeedDao
{
	public boolean insertRSSFeed(RSSFeed feed);
	
	RSSFeed getRSSFeed(long ownerId);
}
