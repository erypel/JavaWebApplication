package com.javawebapp.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javawebapp.dao.WalletDao;
import com.javawebapp.hibernate.HibernateUtil;
import com.javawebapp.model.Wallet;

public class WalletDaoImpl implements WalletDao
{
	Logger logger = LogManager.getLogger(WalletDaoImpl.class);
	
	// column names
	private static final String OWNERID = "ownerId";
	
	@Override
	public Wallet getWallet(long userId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Wallet> results = Collections.emptyList();
		try
		{
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Wallet> cq = cb.createQuery(Wallet.class);
			Root<Wallet> root = cq.from(Wallet.class);
			cq.select(root).where(cb.equal(root.get(OWNERID), userId));
			Query<Wallet> query = session.createQuery(cq);
			results = query.getResultList();
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error getting user(String).", e);
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
	
	@Override
	public Wallet createWallet(long ownerId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Wallet wallet = null;
		try
		{
			session.beginTransaction();
			wallet = new Wallet(ownerId); // important. need to create wallet here
			session.save(wallet);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error inserting wallet.", e);
		}
		finally
		{
			session.close();
		}
		return wallet;
	}
}
