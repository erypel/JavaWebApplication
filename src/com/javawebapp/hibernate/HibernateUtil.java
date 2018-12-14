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
	private static final EntityManagerFactory persistenceEntityManagerFactory = buildPersistenceEntityManagerFactory();
	
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
	
	private static EntityManagerFactory buildPersistenceEntityManagerFactory()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistence");
		return emf;
	}
	
	public static SessionFactory getSessionFactory()
	{
		if(sessionFactory == null)
			buildSessionFactory();
		return sessionFactory;
	}
	
	public static EntityManagerFactory getPersistenceEntityManagerFactory()
	{
		return persistenceEntityManagerFactory;
	}
	
	public static void shutdownSessionFactory()
	{
		// Close caches and connection pools
		getSessionFactory().close();
	}
	
	public static void closePersistenceEntityManagerFactory()
	{
		getPersistenceEntityManagerFactory().close();
	}
}