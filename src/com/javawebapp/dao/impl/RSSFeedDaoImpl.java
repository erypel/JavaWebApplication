package com.javawebapp.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javawebapp.dao.RSSFeedDao;
import com.javawebapp.hibernate.HibernateUtil;
import com.javawebapp.model.Podcast;
import com.javawebapp.model.RSSFeed;
import com.javawebapp.model.RSSFeedMessage;
import com.javawebapp.model.User;

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

	@Override
	public RSSFeed getRSSFeed(long ownerId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<RSSFeed> results = Collections.emptyList();
		try
		{
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RSSFeed> cq = cb.createQuery(RSSFeed.class);
			Root<RSSFeed> root = cq.from(RSSFeed.class);
			cq.select(root).where(cb.equal(root.get("OWNERID"), ownerId));
			Query<RSSFeed> query = session.createQuery(cq);
			results = query.getResultList();
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			//TODO log here
		}
		finally
		{
			session.close();
		}

		if(results.isEmpty())
			return null;
		else
			return results.get(0);
	}
}
