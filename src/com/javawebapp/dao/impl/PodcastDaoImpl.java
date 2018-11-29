package com.javawebapp.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javawebapp.dao.PodcastDao;
import com.javawebapp.hibernate.HibernateUtil;
import com.javawebapp.model.Podcast;
import com.javawebapp.model.User;

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

	@Override
	public List<Podcast> get50Podcasts()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Podcast> results = Collections.emptyList();
		try
		{
			logger.info("Made call to get50Podcasts()"); 
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Podcast> cq = cb.createQuery(Podcast.class);
			Root<Podcast> root = cq.from(Podcast.class);
			cq.select(root);
			Query<Podcast> query = session.createQuery(cq);
			query.setMaxResults(50);
			results = query.getResultList();
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error getting 50 Podcasts.", e);
		}
		finally
		{
			session.close();
		}
		return results;
	}

	@Override
	public Podcast getPodcast(long id)
	{
		// TODO utilize EntityManager
		// will need to configure persistence.xml
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Podcast> results = Collections.emptyList();
		try
		{
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Podcast> cq = cb.createQuery(Podcast.class);
			Root<Podcast> root = cq.from(Podcast.class);
			cq.select(root).where(cb.equal(root.get("ID"), id));
			Query<Podcast> query = session.createQuery(cq);
			results = query.getResultList();
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error getting podcast.", e);
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
