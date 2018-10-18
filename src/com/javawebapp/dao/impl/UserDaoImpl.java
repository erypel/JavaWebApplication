package com.javawebapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javawebapp.dao.UserDao;
import com.javawebapp.db.ConnectionUtils;
import com.javawebapp.objects.User;

//TODO queries to a queries.properties file https://stackoverflow.com/questions/370818/cleanest-way-to-build-an-sql-string-in-java
public class UserDaoImpl implements UserDao {

	@Override
	public List<User> getAllUsers() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			connection.setAutoCommit(false); // best practice
			ps = connection.prepareStatement("SELECT * FROM User ORDER BY id ASC;");
			rs = ps.executeQuery();
			connection.commit();
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				String email = rs.getString("EMAIL");
				Long id = rs.getLong("ID");
				User user = new User(username, email, password, id);
				users.add(user);
			}
			return users;
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
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
		return null; // TODO can we return an empty result set?
	}

	@Override
	public User getUser(long id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			connection.setAutoCommit(false); // best practice
			ps = connection.prepareStatement("SELECT * FROM User WHERE id=?;");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			connection.commit();
			if (rs.next()) {
				String username = rs.getString("USERNAME");
				String email = rs.getString("EMAIL");
				String password = rs.getString("PASSWORD");
				return new User(username, email, password, id);
			} else {
				System.out.println("No user found for id=" + Long.toString(id));
			}
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
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public User getUser(String username) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			connection.setAutoCommit(false); // best practice
			ps = connection.prepareStatement("SELECT * FROM User WHERE username=?;");
			ps.setString(1, username);
			rs = ps.executeQuery();
			connection.commit();
			if (rs.next()) {
				Long id = rs.getLong("ID");
				String email = rs.getString("EMAIL");
				String password = rs.getString("PASSWORD");
				return new User(username, email, password, id);
			} else {
				System.out.println("No user found for username=" + username);
			}
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
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}

		return null;
	}
	
	//TODO add test
	@Override
	public User getUser(String username, String password) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			connection.setAutoCommit(false); // best practice
			ps = connection.prepareStatement("SELECT * FROM User WHERE username=? AND password=?;");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			connection.commit();
			if (rs.next()) {
				Long id = rs.getLong("ID");
				String email = rs.getString("EMAIL");
				return new User(username, email, password, id);
			} else {
				System.out.println("No user found for username=" + username);
			}
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
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}

		return null;
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
	public void deleteUser(String username) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			connection.setAutoCommit(false); // best practice
			ps = connection.prepareStatement("DELETE FROM User WHERE username=?;");
			ps.setString(1, username);
			int rowsUpdated = ps.executeUpdate();
			connection.commit();
			System.out.println("Deleted " + rowsUpdated + " rows in the User table.");
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
	public void deleteUser(long id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			connection.setAutoCommit(false); // best practice
			ps = connection.prepareStatement("DELETE FROM User WHERE id=?;");
			ps.setLong(1, id);
			int rowsUpdated = ps.executeUpdate();
			connection.commit();
			System.out.println("Deleted " + rowsUpdated + " rows in the User table.");
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
	//TODO create test and create a way to programmatically assign ids
	public void insertUser(String username, String password, String email) {
		insertUser(username, password, email, 1L);
	}
}
