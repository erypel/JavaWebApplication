package com.javawebapp.dao;

import java.util.List;

import com.javawebapp.model.Podcast;

public interface PodcastDao
{
	public boolean insertPodcast(Podcast podcast);
	
	public List<Podcast> get50Podcasts();
	
	public Podcast getPodcast(long id);
}
