package com.javawebapp.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javawebapp.dao.UserDao;
import com.javawebapp.hibernate.HibernateUtil;
import com.javawebapp.model.User;
import com.javawebapp.util.JavaWebAppUtils;

public class UserDaoImpl implements UserDao
{
	Logger logger = LogManager.getLogger(UserDaoImpl.class);
	
	// column names
	private static final String PASSWORD = "password";
	private static final String USERNAME = "userName";
	
	@Override
	public List<User> getAllUsers()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<User> results = Collections.emptyList();
		try
		{
			logger.info("Made call to getAllUsers()"); 
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> root = cq.from(User.class);
			cq.select(root);
			Query<User> query = session.createQuery(cq);
			results = query.getResultList();
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error getting all users.", e);
		}
		finally
		{
			session.close();
		}
		return results;
		
	}
	
	@Override
	public User getUser(long id)
	{
		EntityManager em = HibernateUtil.getPersistenceEntityManagerFactory().createEntityManager();
		User user = em.find(User.class, id);
		em.close();
		return user;
	}
	
	@Override
	public User getUser(String userName)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<User> results = Collections.emptyList();
		try
		{
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> root = cq.from(User.class);
			cq.select(root).where(cb.equal(root.get(USERNAME), userName));
			Query<User> query = session.createQuery(cq);
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
	public User getUser(String userName, String password)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<User> results = Collections.emptyList();
		try
		{
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> root = cq.from(User.class);
			cq.select(root)
					.where(cb.and(cb.equal(root.get(USERNAME), userName), cb.equal(root.get("password"), password)));
			Query<User> query = session.createQuery(cq);
			results = query.getResultList();
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error getting user(userName, password).", e);
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
	public void updateUsername(String oldUserName, String newUserName)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<User> update = cb.createCriteriaUpdate(User.class);
			Root<User> root = update.from(User.class);
			update.set(root.get(USERNAME), newUserName).where(cb.equal(root.get("userName"), oldUserName));
			session.createQuery(update).executeUpdate();
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error updating user username(oldUserName, newUserName).", e);
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void updateUsername(long id, String newUserName)
	{
		EntityManager em = HibernateUtil.getPersistenceEntityManagerFactory().createEntityManager();
		User user = em.find(User.class, id);
		em.getTransaction().begin();
		user.setUserName(newUserName);
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public void deleteUser(String userName)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaDelete<User> cd = cb.createCriteriaDelete(User.class);
			Root<User> root = cd.from(User.class);
			cd.where(cb.equal(root.get(USERNAME), userName));
			session.createQuery(cd).executeUpdate();
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error deleting user(String).", e);
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void deleteUser(long id)
	{
		EntityManager em = HibernateUtil.getPersistenceEntityManagerFactory().createEntityManager();
		User user = em.find(User.class, id);
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public void updateUserPassword(String userName, String newPassword)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<User> update = cb.createCriteriaUpdate(User.class);
			Root<User> root = update.from(User.class);
			update.set(root.get(PASSWORD), newPassword).where(cb.equal(root.get("userName"), userName));
			session.createQuery(update).executeUpdate();
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error updating user password(userName, newPassword).", e);
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void updateUserPassword(long id, String newPassword)
	{
		EntityManager em = HibernateUtil.getPersistenceEntityManagerFactory().createEntityManager();
		User user = em.find(User.class, id);
		em.getTransaction().begin();
		user.setPassword(newPassword);
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public void insertUser(String userName, String password, String email, Long id)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			session.beginTransaction();
			User user = new User(userName, password, email, id);
			session.save(user);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			logger.error("Error inserting user.", e);
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void insertUser(String username, String password, String email)
	{
		insertUser(username, password, email, JavaWebAppUtils.generateUniqueId());
	}
}
