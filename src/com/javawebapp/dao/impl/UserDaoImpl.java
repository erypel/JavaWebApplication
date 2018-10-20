package com.javawebapp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javawebapp.dao.UserDao;
import com.javawebapp.hibernate.HibernateUtil;
import com.javawebapp.objects.User;
import com.javawebapp.util.JavaWebAppUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public List<User> getAllUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.select(root);
		Query<User> query = session.createQuery(cq); 
		List<User> results = query.getResultList();
		session.close();
		return results;
		
	}

	@Override
	public User getUser(long id) {
		EntityManager em = HibernateUtil.getUserEntityManagerFactory().createEntityManager();
		User user = em.find(User.class, id);
		em.close();
		return user;
	}

	@Override
	public User getUser(String userName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.select(root).where(cb.equal(root.get("userName"), userName));
		Query<User> query = session.createQuery(cq);
		List<User> results = query.getResultList();
		session.close();
		if(results.isEmpty())
			return null;
		else
			return results.get(0);
	}
	
	@Override
	public User getUser(String userName, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.select(root).where(cb.and(cb.equal(root.get("userName"), userName), cb.equal(root.get("password"), password)));
		Query<User> query = session.createQuery(cq);
		List<User> results = query.getResultList();
		session.close();
		if(results.isEmpty())
			return null;
		else
			return results.get(0);
	}

	@Override
	public void updateUsername(String oldUserName, String newUserName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaUpdate<User> update = cb.createCriteriaUpdate(User.class);
		Root<User> root = update.from(User.class);
		update.set(root.get("userName"), newUserName).where(cb.equal(root.get("userName"), oldUserName));
		session.createQuery(update).executeUpdate();
		session.close();
	}

	@Override
	public void updateUsername(long id, String newUserName) {
		EntityManager em = HibernateUtil.getUserEntityManagerFactory().createEntityManager();
		User user = em.find(User.class, id);
		em.getTransaction().begin();
		user.setUserName(newUserName);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteUser(String userName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaDelete<User> cd = cb.createCriteriaDelete(User.class);
		Root<User> root = cd.from(User.class);
		cd.where(cb.equal(root.get("userName"), userName));
		session.createQuery(cd).executeUpdate();
		session.close();
	}

	@Override
	public void deleteUser(long id) {
		EntityManager em = HibernateUtil.getUserEntityManagerFactory().createEntityManager();
		User user = em.find(User.class, id);
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void updateUserPassword(String userName, String newPassword) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaUpdate<User> update = cb.createCriteriaUpdate(User.class);
		Root<User> root = update.from(User.class);
		update.set(root.get("password"), newPassword).where(cb.equal(root.get("userName"), userName));
		session.createQuery(update).executeUpdate();
		session.close();
	}

	@Override
	public void updateUserPassword(long id, String newPassword) {
		EntityManager em = HibernateUtil.getUserEntityManagerFactory().createEntityManager();
		User user = em.find(User.class, id);
		em.getTransaction().begin();
		user.setPassword(newPassword);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void insertUser(String userName, String password, String email, Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
	     
	     User user = new User(userName, email, password, id);
	     
	     session.save(user);
	     
	     session.getTransaction().commit();
	     session.close();
	}

	
	@Override
	public void insertUser(String username, String password, String email) {
		insertUser(username, password, email, JavaWebAppUtils.generateUniqueId());
	}
}
