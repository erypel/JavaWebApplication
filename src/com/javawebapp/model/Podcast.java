package com.javawebapp.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	
	@ColumnDefault( value = "default" )
	@Column(name = "EPISODENAME", nullable = false)
	private String episodeName;
	
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	private long ID;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@ColumnDefault( value = "default" ) //TODO make real
	@Column(name = "PATH", nullable = false)
	private String path;
	
	public Podcast()
	{
		
	}
	
	public Podcast(String episodeName, String description, String path)
	{
		this.episodeName = episodeName;
		this.ID = JavaWebAppUtils.generateUniqueId();
		this.description = description;
		this.path = path;
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
	
	//TODO this is broken
	public static boolean savePodcastToFileStore(InputStream is, String path)
	{
		OutputStream os = null;
		try
		{
			os = new FileOutputStream(new File(path));
			
			int read = 0;
			//TODO is this the right size for the byte array?
			byte[] bytes = new byte[1024];
			
			while ((read = is.read(bytes)) != -1)
			{
				os.write(bytes, 0, read);
			}
		}
		catch (IOException e) {
			e.printStackTrace(); //TODO log
			return false;
		}
		finally
		{
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		return true;
	}

	public static String constructPodcastPath(String episodeName)
	{
		String basePath = "http://localhost:8080/JavaWebApplication/uploads/";
		//TODO check for uniqueness and save as an appropriate file
		return basePath + episodeName + ".wav";
	}
}
