package com.javawebapp.servlet.session;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javawebapp.model.Podcast;
import com.javawebapp.service.PodcastService;
import com.javawebapp.service.RSSFeedDataService;

@WebServlet("/uploadPodcastServlet")
@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class FileUploadDBServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	PodcastService podcastService = new PodcastService();
	RSSFeedDataService rssFeedDataService = new RSSFeedDataService();
	
	Logger logger = LogManager.getLogger(FileUploadDBServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		long ownerId = Long.parseLong(session.getAttribute("userId").toString());
		// get field values
		String episodeName = request.getParameter("episodeName");
		String episodeDescription = request.getParameter("episodeDescription");
		String episodePath = podcastService.constructPodcastPath(episodeName);
		
		// TODO find out if this is compatible with audio
		InputStream is = null; // input stream of the upload audio file
		
		Part filePart = request.getPart("podcastEpisode");
		
		if(filePart != null)
		{
			logger.info("Uploading file with name::" + filePart.getName());
			logger.info("Size::" + filePart.getSize());
			logger.info("Content Type::" + filePart.getContentType());
			
			// get the input stream of the upload file
			is = filePart.getInputStream();
		}
		
		String message = null; // message to send back to client
		
		try
		{
			Podcast podcast = new Podcast(episodeName, episodeDescription, episodePath, ownerId);
			if(podcastService.insertPodcast(podcast))
			{
				rssFeedDataService.createRSSFeedMessage(podcast); // depends on podcast existing in the podcasts table
				rssFeedDataService.appendMessageToFeed(podcast);
				podcastService.savePodcastToFileStore(is, episodePath);
				// TODO store upload in files system outside DB
				message = "Success!";
			}
			else
			{
				message = "Error uploading podcast file";
				logger.error("Error uploading podcast file");
			}
		}
		catch(Exception e)
		{
			message = e.getMessage();
		}
		finally
		{
			if(is != null)
			{
				try
				{
					is.close();
				}
				catch(Exception e)
				{
					logger.error("error closing input stream", e);
				}
			}
			// set message in request scope
			request.setAttribute("message", message);

			//For debugging
			ServletContext c = getServletContext();
			String s = c.getContextPath();
			
			// forwards to the message page
			getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}
}
