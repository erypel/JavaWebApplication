package com.javawebapp.servlet.session;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javawebapp.dao.impl.PodcastDaoImpl;
import com.javawebapp.db.ConnectionUtils;
import com.javawebapp.model.Podcast;

@WebServlet("/uploadPodcastServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class FileUploadDBServlet extends HttpServlet
{
	Logger logger = LogManager.getLogger(FileUploadDBServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//get field values
		String episodeName = request.getParameter("episodeName");
		String episodeDescription = request.getParameter("episodeDescription");
		String episodePath = Podcast.constructPodcastPath(episodeName);
		
		//TODO find out if this is compatible with audio
		InputStream is = null; //input stream of the upload audio file
		
		Part filePart = request.getPart("podcastEpisode");
		
		if(filePart != null) {
			logger.info("Uploading file with name::" + filePart.getName());
			logger.info("Size::"+filePart.getSize());
			logger.info("Content Type::" + filePart.getContentType());
		
			// get the input stream of the upload file
			is = filePart.getInputStream();
		}
		
		Connection conn = null; // connection to DB
		String message = null; // message to send back to client
		
		try
		{
			conn = ConnectionUtils.getMyConnection();
			Podcast podcast = new Podcast(episodeName, episodeDescription, episodePath);
			PodcastDaoImpl podcastDao = new PodcastDaoImpl();
			podcastDao.insertPodcast(podcast);
			//TODO store upload in files system outside DB
			message = "Success!";
		}
		catch(Exception e)
		{
			message = "Error uploading podcast file " + e.getMessage();
			logger.error("Error uploading podcast file", e);
		}
		finally
		{
			//close db connection
			if(conn != null)
			{
				try
				{
					conn.close();
				}
				catch(Exception e)
				{
					logger.error("error closing connection", e);
				}
			}
			
			//set message in request scope
			request.setAttribute("message", message);
			
			// forwards to the message page
            getServletContext().getRequestDispatcher("jsps/Message.jsp").forward(request, response);
		}
	}
}
