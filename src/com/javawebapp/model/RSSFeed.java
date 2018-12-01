package com.javawebapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.javawebapp.util.JavaWebAppUtils;

/**
 * Stores an RSS feed
 *
 */
@Entity
@Table(name = "RSSFEEDS")
public class RSSFeed implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "TITLE", nullable = false)
	final String title;
	@Column(name = "LINK", nullable = false)
	final String link;
	@Column(name = "DESCRIPTION", nullable = false)
	final String description;
	@Column(name = "LANGUAGE", nullable = false)
	final String language;
	@Column(name = "COPYRIGHT", nullable = false)
	final String copyright;
	@Column(name = "PUBLISHDATEUTC", nullable = false)
	final String publishDate;
	@ColumnDefault( value = "path" )
	@Column(name = "PATH", nullable = false)
	String path = "path"; //TODO make real
	@Id
	@Column(name = "OWNERID", updatable = true, nullable = false)
	long ownerId;
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	final long id;
	
	@ElementCollection(targetClass=RSSFeedMessage.class)
	final List<RSSFeedMessage> entries = new ArrayList<RSSFeedMessage>();
	
	/**
	 * for the data layer
	 * @param title
	 * @param link
	 * @param description
	 * @param language
	 * @param copyright
	 * @param publishDate
	 * @param ownerId
	 */
	public RSSFeed(String title, String link, String description, String language, String copyright, String publishDate, long ownerId)
	{
		this.title = title;
		this.link = link;
		this.description = description;
		this.language = language;
		this.copyright = copyright;
		this.publishDate = publishDate;
		this.ownerId = ownerId;
		this.id = JavaWebAppUtils.generateUniqueId();
	}
	
	/**
	 * for the RSSFeedParser
	 * @param title
	 * @param link
	 * @param description
	 * @param language
	 * @param copyright
	 * @param publishDate
	 */
	public RSSFeed(String title, String link, String description, String language, String copyright, String publishDate)
	{
		this.title = title;
		this.link = link;
		this.description = description;
		this.language = language;
		this.copyright = copyright;
		this.publishDate = publishDate;
		this.id = JavaWebAppUtils.generateUniqueId();
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
	
	public long getOwnerId()
	{
		return ownerId;
	}

	public void setOwnerId(long ownerId)
	{
		this.ownerId = ownerId;
	}

	public long getId()
	{
		return id;
	}
	
	@Override
	public String toString()
	{
		return "Feed [copyright=" + copyright + ", description=" + description + ", language=" + language + ", link="
				+ link + ", publishDate=" + publishDate + ", title=" + title + "]";
	}
}
