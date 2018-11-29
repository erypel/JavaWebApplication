package com.javawebapp.hibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.javawebapp.db.ConnectionUtils;

public class HibernateUtil
{
	static Logger logger = LogManager.getLogger(HibernateUtil.class);
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static final EntityManagerFactory userEntityManagerFactory = buildUserEntityManagerFactory();
	//private static final EntityManagerFactory podcastEntityManagerFactory = buildPodcastEntityManagerFactory();
	
	private static SessionFactory buildSessionFactory()
	{
		try
		{
			// Create the session factory from hibernate.cfg.xml
			SessionFactory sf = new Configuration().configure().buildSessionFactory();
			return sf;
		}
		catch(Exception e)
		{
			logger.error("SessionFactory create failed.", e);
		}
		return null;
	}
	/*
	private static EntityManagerFactory buildPodcastEntityManagerFactory()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Podcast");
		return emf;
	}
	
	public static EntityManagerFactory getPodcastEntityManagerFactory()
	{
		return podcastEntityManagerFactory;
	}*/
	
	// Create and entity manager factory for the User entity
	private static EntityManagerFactory buildUserEntityManagerFactory()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("User");
		return emf;
	}
	
	public static SessionFactory getSessionFactory()
	{
		if(sessionFactory == null)
			buildSessionFactory();
		return sessionFactory;
	}
	
	public static EntityManagerFactory getUserEntityManagerFactory()
	{
		return userEntityManagerFactory;
	}
	
	public static void shutdownSessionFactory()
	{
		// Close caches and connection pools
		getSessionFactory().close();
	}
	
	public static void closeUserEntityManagerFactory()
	{
		getUserEntityManagerFactory().close();
	}
}