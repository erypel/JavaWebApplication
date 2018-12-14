package com.javawebapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.javawebapp.util.JavaWebAppUtils;

@Entity
@Table(name = "PODCASTS")
public class Podcast implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ColumnDefault(value = "default")
	@Column(name = "EPISODENAME", nullable = false)
	private String episodeName;
	
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	private long ID;
	
	@Id
	@Column(name = "OWNERID", updatable = true, nullable = false)
	private long ownerId;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@ColumnDefault(value = "default") // TODO make real
	@Column(name = "PATH", nullable = false)
	private String path;
	
	public Podcast()
	{
		
	}
	
	public Podcast(String episodeName, String description, String path, Long ownerId)
	{
		this.episodeName = episodeName;
		this.ID = JavaWebAppUtils.generateUniqueId();
		this.description = description;
		this.path = path;
		this.ownerId = ownerId;
	}
	
	public String getEpisodeName()
	{
		return episodeName;
	}
	
	public void setEpisodeName(String episodeName)
	{
		this.episodeName = episodeName;
	}
	
	public long getID()
	{
		return ID;
	}
	
	public void setID(long iD)
	{
		ID = iD;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
	
	public long getOwnerId()
	{
		return ownerId;
	}
	
	public void setOwnerId(long id)
	{
		this.ownerId = id;
	}
}
