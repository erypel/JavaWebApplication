package com.javawebapp.objects;

public class User 
{
	private String username;
	private long ID;
	private String email;
	private String password;
	
	User(String username, String email, String password) 
	{
		this.username = username;
		this.ID = 1L;
		this.email = email;
		this.password = password;
	}
	
	public String getUserName()
	{
		return username;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public long getId()
	{
		return ID;
	}
}
