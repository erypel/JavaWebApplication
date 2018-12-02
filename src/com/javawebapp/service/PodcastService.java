package com.javawebapp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.javawebapp.dao.impl.PodcastDaoImpl;
import com.javawebapp.model.Podcast;
import com.javawebapp.servlet.session.FileUploadDBServlet;

@Component
public class PodcastService
{
	Logger logger = LogManager.getLogger(PodcastService.class);
	PodcastDaoImpl podcastDao = new PodcastDaoImpl();
	
	public Podcast getPodcast(long id)
	{
		return podcastDao.getPodcast(id);
	}
	
	public boolean insertPodcast(Podcast podcast)
	{
		return podcastDao.insertPodcast(podcast);
	}
	
	// TODO this is broken
	public boolean savePodcastToFileStore(InputStream is, String path)
	{
		OutputStream os = null;
		try
		{
			os = new FileOutputStream(new File(path));
			
			int read = 0;
			// TODO is this the right size for the byte array?
			byte[] bytes = new byte[1024];
			
			while((read = is.read(bytes)) != -1)
			{
				os.write(bytes, 0, read);
			}
		}
		catch(IOException e)
		{
			logger.warn("Error saving podcast to filestore: " + e.getMessage());
			logger.info(e.getStackTrace());
			return false;
		}
		finally
		{
			if(os != null)
			{
				try
				{
					os.close();
				}
				catch(IOException e)
				{
					logger.warn("Error closing output stream: " + e.getMessage());
					logger.info(e.getStackTrace());
				}
				
			}
		}
		return true;
	}
	
	public String constructPodcastPath(String episodeName)
	{
		String basePath = "http://localhost:8080/JavaWebApplication/uploads/";
		// TODO check for uniqueness and save as an appropriate file
		return basePath + episodeName + ".wav";
	}
}
