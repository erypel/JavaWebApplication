package com.javawebapp.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try
		{
			// Create the session factory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		}
		catch(Exception e)
		{
			System.out.println("SessionFactory create failed.");
			e.printStackTrace();
		}
		return null;
	}
	
	public static SessionFactory getSessionFactory() 
	{
		if(sessionFactory == null)
		{
			buildSessionFactory();
		}
		return sessionFactory;
	}
	
	public static void shutdown()
	{
		//Close caches and connection pools
		getSessionFactory().close();
	}
}