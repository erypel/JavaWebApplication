package com.javawebapp.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.javawebapp.dao.impl.RSSFeedDaoImpl;
import com.javawebapp.dao.impl.RSSFeedMessageDaoImpl;
import com.javawebapp.dao.impl.UserDaoImpl;
import com.javawebapp.model.Podcast;
import com.javawebapp.model.RSSFeed;
import com.javawebapp.model.RSSFeedMessage;
import com.javawebapp.model.User;
import com.javawebapp.service.RSSFeedWriterService;

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
	
	//TODO can probably clean up calls to the db
	public boolean deleteMessageFromFeed(Podcast podcast)
	{
		try
		{
			RSSFeed feed = rssFeedDao.getRSSFeed(podcast.getOwnerId());
			User user = userDao.getUser(podcast.getOwnerId());
			RSSFeedWriterService writer = new RSSFeedWriterService(feed, getFilePath(feed));
			RSSFeedMessage message = new RSSFeedMessage(podcast.getEpisodeName(), podcast.getDescription(),
					podcast.getPath(), user.getUserName(), feed.getId(), podcast.getID());
			writer.deleteMessage(message);
			return true;
		}
		catch(Exception e)
		{
			// TODO log
			e.printStackTrace();
			return false;
		}
	}
	
	//TODO can probably clean up calls to the db
	public boolean appendMessageToFeed(Podcast podcast)
	{
		try
		{
			RSSFeed feed = rssFeedDao.getRSSFeed(podcast.getOwnerId());
			User user = userDao.getUser(podcast.getOwnerId());
			RSSFeedWriterService writer = new RSSFeedWriterService(feed, getFilePath(feed));
			RSSFeedMessage message = new RSSFeedMessage(podcast.getEpisodeName(), podcast.getDescription(),
					podcast.getPath(), user.getUserName(), feed.getId(), podcast.getID());
			writer.appendMessage(message);
			return true;
		}
		catch(Exception e)
		{
			// TODO log
			e.printStackTrace();
			return false;
		}
		
	}
	
	public String getFilePath(RSSFeed feed)
	{
		String basePath = "C:\\Users\\Evan\\workspace\\JavaWebApplication\\rss\\";
		return basePath + feed.getTitle() + ".xml";
	}
	
	// TODO refactor
	public String buildFilePath(RSSFeed feed)
	{
		String basePath = "C:\\Users\\Evan\\workspace\\JavaWebApplication\\rss\\";
		// TODO check for uniqueness and save as an appropriate file
		File file = new File(basePath + feed.getTitle() + ".xml");
		file.getParentFile().mkdirs();
		try
		{
			FileWriter writer = new FileWriter(file);
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			// TODO log and handle
		}
		return basePath + feed.getTitle() + ".xml";
	}
}
