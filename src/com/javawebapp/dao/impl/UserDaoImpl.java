package com.javawebapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.hibernate.Session;

import com.javawebapp.dao.UserDao;
import com.javawebapp.db.ConnectionUtils;
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.select(root).where(cb.equal(root.get("ID"), id));
		Query<User> query = session.createQuery(cq);
		List<User> results = query.getResultList();
		session.close();
		if(results.isEmpty())
			return null;
		else
			return results.get(0);
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
	public void updateUsername(String oldUsername, String newUsername) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			connection.setAutoCommit(false); // best practice
			ps = connection.prepareStatement("UPDATE User SET username=? WHERE username=?;");
			ps.setString(1, newUsername);
			ps.setString(2, oldUsername);
			int rowsUpdated = ps.executeUpdate();
			connection.commit();
			System.out.println("Updated " + rowsUpdated + " rows in the User table.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				if (connection != null)
					connection.rollback();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}

	@Override
	public void updateUsername(long id, String newUsername) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			connection.setAutoCommit(false); // best practice
			ps = connection.prepareStatement("UPDATE User SET username=? WHERE id=?;");
			ps.setString(1, newUsername);
			ps.setLong(2, id);
			int rowsUpdated = ps.executeUpdate();
			connection.commit();
			System.out.println("Updated " + rowsUpdated + " rows in the User table.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				if (connection != null)
					connection.rollback();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaDelete<User> cd = cb.createCriteriaDelete(User.class);
		Root<User> root = cd.from(User.class);
		cd.where(cb.equal(root.get("ID"), id));
		session.createQuery(cd).executeUpdate();
		session.close();
	}

	@Override
	public void updateUserPassword(String username, String newPassword) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			connection.setAutoCommit(false); // best practice
			ps = connection.prepareStatement("UPDATE User SET password=? WHERE username=?;");
			ps.setString(1, newPassword);
			ps.setString(2, username);
			int rowsUpdated = ps.executeUpdate();
			connection.commit();
			System.out.println("Updated " + rowsUpdated + " rows in the User table.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				if (connection != null)
					connection.rollback();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}

	@Override
	public void updateUserPassword(long id, String newPassword) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			connection.setAutoCommit(false); // best practice
			ps = connection.prepareStatement("UPDATE User SET password=? WHERE id=?;");
			ps.setString(1, newPassword);
			ps.setLong(2, id);
			int rowsUpdated = ps.executeUpdate();
			connection.commit();
			System.out.println("Updated " + rowsUpdated + " rows in the User table.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				if (connection != null)
					connection.rollback();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}

	// TODO logic to handle duplicates
	@Override
	public void insertUser(String username, String password, String email, Long id) {
		Connection connection = null;
		PreparedStatement ps = null;

		String insertUserSql = "INSERT INTO USER (USERNAME, ID, PASSWORD, EMAIL) VALUES (?,?,?,?);";
		try {
			connection = ConnectionUtils.getMyConnection();
			connection.setAutoCommit(false); // best practice
			ps = connection.prepareStatement(insertUserSql);

			ps.setString(1, username);
			ps.setLong(2, id);
			ps.setString(3, password);
			ps.setString(4, email);

			ps.executeUpdate();
			connection.commit();

			System.out.println("Created new user " + username);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (connection != null)
					connection.rollback();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}

	
	@Override
	//TODO create test
	public void insertUser(String username, String password, String email) {
		insertUser(username, password, email, JavaWebAppUtils.generateUniqueId());
	}
}
