package com.javawebapp.db;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {
	public static Connection getMyConnection() throws SQLException, ClassNotFoundException {
		return MySQLConnectionUtils.getMySQLConnection();
	}
	
	/**
	 * Test Connection
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println("Get connection...");
		
		//get a connection object
		Connection connection = ConnectionUtils.getMyConnection();
		
		System.out.println("Got connection " + connection);
		System.out.println("Done");
	}
}
