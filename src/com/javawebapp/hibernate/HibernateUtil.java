package com.javawebapp.hibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static final EntityManagerFactory userEntityManagerFactory = buildUserEntityManagerFactory();
	
	
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
	
	private static EntityManagerFactory buildUserEntityManagerFactory() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("User");
		return emf;
	}
	
	public static EntityManagerFactory getUserEntityManagerFactory()
	{
		return userEntityManagerFactory;
	}

	public static SessionFactory getSessionFactory() 
	{
		return sessionFactory;
	}
	
	public static void shutdownSessionFactory()
	{
		//Close caches and connection pools
		getSessionFactory().close();
	}
	
	public static void closeUserEntityManagerFactory()
	{
		getUserEntityManagerFactory().close();
	}
}