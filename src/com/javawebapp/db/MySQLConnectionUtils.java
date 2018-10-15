package com.javawebapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionUtils {

	public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
		String hostName = "localhost";
		String dbName = "javawebappdb";
		String userName = "root";
		String password = "password";

		return getMySQLConnection(hostName, dbName, userName, password);
	}

	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
			throws SQLException, ClassNotFoundException {
		/**
		 * Declare the class Driver for MySQL DB this is necessary with Java 5 and older
		 * Java 6 and newer will automatically find the appropriate driver the following
		 * line isn't necessary if using > Java 5
		 */
		// Class.forName("com.mysql.jdbc.Drive");

		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

		Connection connection = DriverManager.getConnection(connectionURL, userName, password);

		return connection;
	}
}
