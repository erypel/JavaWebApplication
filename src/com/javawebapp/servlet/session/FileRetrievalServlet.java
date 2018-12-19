package com.javawebapp.servlet.session;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/retrievePodcastServlet")
public class FileRetrievalServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public FileRetrievalServlet()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		File file = new File("C:\\Users\\Evan\\workspace\\JavaWebApplication\\WebContent\\uploads\\testWav.wav");
		
		ServletContext ctx = getServletContext();
		String p = ctx.getContextPath();
		InputStream input = new FileInputStream(file);
		response.setContentType("audio/wav");
		//response.setHeader("Content-Disposition", "inline; filename=\""
		//		+ fileName + "\"");
		
		OutputStream output = response.getOutputStream();
		
		byte[] buffer = new byte[2096];
		int read = 0;
		while((read = input.read(buffer)) != -1)
		{
			output.write(buffer, 0, read);
		}
		
		input.close();
		output.flush();
		output.close();
	}
}
