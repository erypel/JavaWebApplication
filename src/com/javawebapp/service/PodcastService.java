package com.javawebapp.service;

import org.springframework.stereotype.Component;

import com.javawebapp.dao.impl.PodcastDaoImpl;
import com.javawebapp.model.Podcast;

@Component
public class PodcastService
{
	PodcastDaoImpl podcastDao = new PodcastDaoImpl();
	
	public Podcast getPodcast(long id)
	{
		return podcastDao.getPodcast(id);
	}
	
	public boolean insertPodcast(Podcast podcast)
	{
		return podcastDao.insertPodcast(podcast);
	}
}
