package com.javawebapp.db;

import java.sql.Connection;

public class DBConnectionManager {
	private String dbURL;
	private String user;
	private String password;
	private Connection con;
	
	public DBConnectionManager(String url, String user, String pwd)
	{
		this.dbURL = url;
		this.user = user;
		this.password = pwd;
	}
	
	public Connection getConnection() {
		return this.con;
	}
	
	public void closeConnection() {
		//TODO
		//close the DB connection here
	}

}