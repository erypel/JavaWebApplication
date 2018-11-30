package com.javawebapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores an RSS feed
 *
 */
public class RSSFeed
{
	final String title;
	final String link;
	final String description;
	final String language;
	final String copyright;
	final String publishDate;
	
	final List<RSSFeedMessage> entries = new ArrayList<RSSFeedMessage>();
	
	public RSSFeed(String title, String link, String description, String language, String copyright, String publishDate)
	{
		this.title = title;
		this.link = link;
		this.description = description;
		this.language = language;
		this.copyright = copyright;
		this.publishDate = publishDate;
	}

	public List<RSSFeedMessage> getMessages()
	{
		return entries;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getLink()
	{
		return link;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getLanguage()
	{
		return language;
	}
	
	public String getCopyright()
	{
		return copyright;
	}
	
	public String getPublishDate()
	{
		return publishDate;
	}
	
	@Override
	public String toString()
	{
		return "Feed [copyright=" + copyright + ", description=" + description + ", language=" + language + ", link="
				+ link + ", publishDate=" + publishDate + ", title=" + title + "]";
	}
}
