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

import com.javawebapp.dao.RSSFeedMessageDao;
import com.javawebapp.hibernate.HibernateUtil;
import com.javawebapp.model.RSSFeedMessage;
import com.javawebapp.model.User;

public class RSSFeedMessageDaoImpl implements RSSFeedMessageDao
{
	Logger logger = LogManager.getLogger(RSSFeedMessageDaoImpl.class);
	
	@Override
	public boolean insertRSSFeedMessage(RSSFeedMessage message)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			session.beginTransaction();
			session.save(message);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error inserting RSSFeedMessage.", e);
			return false;
		}
		finally
		{
			session.close();
		}
		return true;
	}

	@Override
	public boolean deleteRSSFeedMessage(RSSFeedMessage message)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaDelete<RSSFeedMessage> cd = cb.createCriteriaDelete(RSSFeedMessage.class);
			Root<RSSFeedMessage> root = cd.from(RSSFeedMessage.class);
			cd.where(cb.equal(root.get("ID"), Long.parseLong(message.getGuid())));
			session.createQuery(cd).executeUpdate();
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error deleting RSSFeedMessage.", e);
		}
		finally
		{
			session.close();
		}
		return false;
	}

	@Override
	public RSSFeedMessage getRSSFeedMessage(long podcastId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<RSSFeedMessage> results = Collections.emptyList();
		try
		{
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RSSFeedMessage> cq = cb.createQuery(RSSFeedMessage.class);
			Root<RSSFeedMessage> root = cq.from(RSSFeedMessage.class);
			cq.select(root).where(cb.equal(root.get("PODCASTID"), podcastId));
			Query<RSSFeedMessage> query = session.createQuery(cq);
			results = query.getResultList();
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error getting getting RSSFeedMessage.", e);
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
