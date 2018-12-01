package com.javawebapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.javawebapp.util.JavaWebAppUtils;

/**
 * Represents one RSS message
 */
@Entity
@Table(name = "RSSFEEDMESSAGES")
public class RSSFeedMessage
{
	@Column(name = "EPIDSODENAME", nullable = false)
	String title;
	@Column(name = "DESCRIPTION")
	String description;
	@Column(name = "PATH", nullable = false)
	String link;
	@Column(name = "OWNER", nullable = false)
	String author;
	@Id
	@Column(name = "ID", nullable = false)
	String guid;
	@Id
	@Column(name = "RSSFEEDID", nullable = false)
	long rssFeedId;
	@Id
	@Column(name = "PODCASTID", nullable = false)
	long podcastId;
	
	public RSSFeedMessage()
	{
		
	}
	
	public RSSFeedMessage(String title, String description, String link, String author, long rssFeedId, long podcastId)
	{
		this.title = title;
		this.description = description;
		this.link = link;
		this.author = author;
		this.guid = String.valueOf(JavaWebAppUtils.generateUniqueId());
		this.rssFeedId = rssFeedId;
		this.podcastId = podcastId;
	}
	
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getLink()
	{
		return link;
	}
	public void setLink(String link)
	{
		this.link = link;
	}
	public String getAuthor()
	{
		return author;
	}
	public void setAuthor(String author)
	{
		this.author = author;
	}
	public String getGuid()
	{
		return guid;
	}
	public void setGuid(String guid)
	{
		this.guid = guid;
	}
	
	public long getRSSFeedId()
	{
		return rssFeedId;
	}
	
	public void setRSSFeedId(long rssFeedId)
	{
		this.rssFeedId = rssFeedId;
	}
	
	public long getPodcastId()
	{
		return podcastId;
	}
	
	public void setPodcastId(long podcastId)
	{
		this.podcastId = podcastId;
	}
	
	@Override
	public String toString()
	{
		return "RSSFeedmessage [title=" + title + ", description=" + description + ", link=" + link + ", author=" + author + ", guid=" + guid + "]";
	}
}
