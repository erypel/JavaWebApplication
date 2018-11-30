package com.javawebapp.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.javawebapp.dao.RSSFeedDao;
import com.javawebapp.hibernate.HibernateUtil;
import com.javawebapp.model.RSSFeed;

public class RSSFeedDaoImpl implements RSSFeedDao
{
	Logger logger = LogManager.getLogger(RSSFeedDaoImpl.class);
	
	@Override
	public boolean insertRSSFeed(RSSFeed feed)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			session.beginTransaction();
			session.save(feed);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error inserting RSSFeed.", e);
			return false;
		}
		finally
		{
			session.close();
		}
		return true;
	}
}
