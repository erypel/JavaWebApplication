package com.javawebapp.hibernate;

import org.hibernate.Session;

import com.javawebapp.objects.User;

public class HibernateTest {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		User user = new User("hibernateTest", "password", "test@test.com");
		
		session.save(user);
		session.getTransaction().commit();
	}
}
