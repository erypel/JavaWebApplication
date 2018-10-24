package com.javawebapp.db;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnectionManager
{
	private String dbURL;
	private String user;
	private String password;
	private Connection con;
	
	Logger logger = LogManager.getLogger(DBConnectionManager.class);
	
	public DBConnectionManager(String url, String user, String pwd)
	{
		this.dbURL = url;
		this.user = user;
		this.password = pwd;
	}
	
	public Connection getConnection()
	{
		return this.con;
	}
	
	public void closeConnection()
	{
		try
		{
			this.con.close();
		}
		catch(Exception e)
		{
			logger.error("Error closing DB Connection.", e);
		}
	}
	/*
	 * public void rollback() { try { this.con.rollback(); } catch(Exception e) {
	 * e.printStackTrace(); } }
	 */
}