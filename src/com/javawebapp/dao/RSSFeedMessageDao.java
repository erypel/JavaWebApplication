package com.javawebapp.dao;

import com.javawebapp.model.RSSFeedMessage;

public interface RSSFeedMessageDao
{
	RSSFeedMessage getRSSFeedMessage(long podcastId);
	
	boolean deleteRSSFeedMessage(RSSFeedMessage message);
	
	boolean insertRSSFeedMessage(RSSFeedMessage message);
	
}
