package com.javawebapp.model;

/**
 * Represents one RSS message
 */
public class RSSFeedMessage
{
	String title;
	String description;
	String link;
	String author;
	String guid;
	
	public RSSFeedMessage()
	{
		
	}
	
	public RSSFeedMessage(String title, String description, String link, String author, String guid)
	{
		this.title = title;
		this.description = description;
		this.link = link;
		this.author = author;
		this.guid = guid; //TODO probably wanna generate this
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
	
	@Override
	public String toString()
	{
		return "RSSFeedmessage [title=" + title + ", description=" + description + ", link=" + link + ", author=" + author + ", guid=" + guid + "]";
	}
}
