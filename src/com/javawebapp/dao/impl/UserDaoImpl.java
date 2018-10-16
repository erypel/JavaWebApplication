package com.javawebapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javawebapp.dao.UserDao;
import com.javawebapp.db.ConnectionUtils;
import com.javawebapp.objects.User;

//TODO after finishing this write some tests!
//TODO queries to a queries.properties file https://stackoverflow.com/questions/370818/cleanest-way-to-build-an-sql-string-in-java
public class UserDaoImpl implements UserDao {

	@Override
	public ResultSet getAllUsers() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			ps = connection.prepareStatement("SELECT * FROM User;");
			rs = ps.executeQuery();
			return rs;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			ps = connection.prepareStatement("SELECT * FROM User WHERE id=?;");
			ps.setLong(1, id);
			rs = ps.executeQuery();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			ps = connection.prepareStatement("SELECT * FROM User WHERE username=?;");
			ps.setString(1, username);
			rs = ps.executeQuery();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			ps = connection.prepareStatement("UPDATE User SET username=? WHERE username=?;");
			ps.setString(1, newUsername);
			ps.setString(2, oldUsername);
			ps.executeQuery();
			int rowsUpdated = ps.executeUpdate();
			System.out.println("Updated " + rowsUpdated + " rows in the User table.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void updateUsername(long id, String newUsername) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			ps = connection.prepareStatement("UPDATE User SET username=? WHERE id=?;");
			ps.setString(1, newUsername);
			ps.setLong(2, id);
			ps.executeQuery();
			int rowsUpdated = ps.executeUpdate();
			System.out.println("Updated " + rowsUpdated + " rows in the User table.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void deleteUser(String username) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			ps = connection.prepareStatement("DELETE User WHERE username=?;");
			ps.setString(1, username);
			ps.executeQuery();
			int rowsUpdated = ps.executeUpdate();
			System.out.println("Deleted " + rowsUpdated + " rows in the User table.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void deleteUser(long id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			ps = connection.prepareStatement("DELETE User WHERE id=?;");
			ps.setLong(1, id);
			ps.executeQuery();
			int rowsUpdated = ps.executeUpdate();
			System.out.println("Deleted " + rowsUpdated + " rows in the User table.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void updateUserPassword(String username, String newPassword) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			ps = connection.prepareStatement("UPDATE User SET password=? WHERE username=?;");
			ps.setString(1, newPassword);
			ps.setString(2, username);
			ps.executeQuery();
			int rowsUpdated = ps.executeUpdate();
			System.out.println("Updated " + rowsUpdated + " rows in the User table.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void updateUserPassword(long id, String newPassword) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionUtils.getMyConnection();
			ps = connection.prepareStatement("UPDATE User SET password=? WHERE username=?;");
			ps.setString(1, newPassword);
			ps.setLong(2, id);
			ps.executeQuery();
			int rowsUpdated = ps.executeUpdate();
			System.out.println("Updated " + rowsUpdated + " rows in the User table.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	// TODO logic to handle duplicates
	@Override
	public void insertUser(String username, String password, String email, Long id) {
		Connection connection = null;
		PreparedStatement ps = null;

		String insertUserSql = "INSERT INTO User (USERNAME, ID, PASSWORD, EMAIL) VALUES (?,?,?,?);";
		try {
			connection = ConnectionUtils.getMyConnection();
			ps = connection.prepareStatement(insertUserSql);

			ps.setString(1, username);
			ps.setLong(2, id);
			ps.setString(3, password);
			ps.setString(4, email);

			ps.executeUpdate();

			System.out.println("Created new user " + username);
		} catch (SQLException | ClassNotFoundException e) {
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
}
