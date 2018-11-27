package com.javawebapp.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.javawebapp.dao.PodcastDao;
import com.javawebapp.hibernate.HibernateUtil;
import com.javawebapp.model.Podcast;

public class PodcastDaoImpl implements PodcastDao
{
	Logger logger = LogManager.getLogger(PodcastDaoImpl.class);
	
	@Override
	public boolean insertPodcast(Podcast podcast)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			session.beginTransaction();
			session.save(podcast);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error inserting podcast.", e);
			return false;
		}
		finally
		{
			session.close();
		}
		return true;
	}
}
