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

import com.javawebapp.dao.LocalXRPLedgerDao;
import com.javawebapp.factory.base.BaseLocalXRPLedgerFactory;
import com.javawebapp.factory.impl.LocalXRPLedgerFactory;
import com.javawebapp.hibernate.HibernateUtil;
import com.javawebapp.model.LocalXRPLedger;

public class LocalXRPLedgerDaoImpl implements LocalXRPLedgerDao
{
	Logger logger = LogManager.getLogger(LocalXRPLedgerDaoImpl.class);
	
	// The wallet factory
	BaseLocalXRPLedgerFactory factory = new LocalXRPLedgerFactory();
	
	// column names
	private static final String OWNERID = "ownerId";
	
	@Override
	public LocalXRPLedger getWallet(long ownerId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<LocalXRPLedger> results = Collections.emptyList();
		try
		{
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<LocalXRPLedger> cq = cb.createQuery(LocalXRPLedger.class);
			Root<LocalXRPLedger> root = cq.from(LocalXRPLedger.class);
			cq.select(root).where(cb.equal(root.get(OWNERID), ownerId));
			Query<LocalXRPLedger> query = session.createQuery(cq);
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
	
	//TODO could implement this to solely retrieve the balance instead of the whole wallet. might be quicker/more effecient
	@Override
	public String getWalletBalance(long ownerID)
	{
		LocalXRPLedger wallet = getWallet(ownerID);
		return wallet.getFunds();
	}
		
	@Override
	public LocalXRPLedger createWallet(long ownerId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		LocalXRPLedger wallet = null;
		try
		{
			session.beginTransaction();
			wallet = factory.createLocalXRPLedger(ownerId); // important. need to create wallet here
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
