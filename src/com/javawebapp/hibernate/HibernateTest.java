package com.javawebapp.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;

import com.javawebapp.model.User;

public class HibernateTest
{
	private static final String PERSISTENCE_UNIT_NAME = "User";
	private static EntityManagerFactory emf;
	
	public static void main(String[] args)
	{
		System.out.print("==Begin EntityManager test===");
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		// Read the existing entries and write to console
		Query q = em.createQuery("SELECT u FROM User u");
		List<User> userList = q.getResultList();
		for(User user : userList)
		{
			System.out.println(user.getUserName());
		}
		System.out.println("Size: " + userList.size());
		
		// Create new user
		em.getTransaction().begin();
		User user1 = new User();
		user1.setUserName("Tom_Johnson");
		user1.setEmail("tomj@tom.com");
		user1.setPassword("pass");
		em.persist(user1);
		em.getTransaction().commit();
		em.close();
		
		System.out.print("==Finished EntityManager test===");
		System.out.print("==Begin Hibernate test===");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		User user2 = new User("hibernateTest", "password", "test@test.com");
		
		session.save(user2);
		session.getTransaction().commit();
		session.close();
		
		System.out.print("==Finished Hibernate test===");
	}
}
