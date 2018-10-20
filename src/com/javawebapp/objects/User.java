package com.javawebapp.objects;

import java.io.Serializable;

import javax.persistence.*;

import com.javawebapp.util.JavaWebAppUtils;

@Entity
@Table(name = "USER")
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "USERNAME")
	private String userName;
	
	@Id
	@Column(name = "ID", updatable = false, nullable = false)
	private long ID;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PASSWORD")
	private String password;
	
	// Constructors
	public User()
	{
		
	}
	
	public User(String username, String password, String email)
	{
		this.userName = username;
		this.password = password;
		this.email = email;
		this.ID = JavaWebAppUtils.generateUniqueId();
	}
	
	public User(String username, String password, String email, Long id)
	{
		this.userName = username;
		this.password = password;
		this.email = email;
		this.ID = id;
	}
	
	// Getters and Setters
	public String getUserName()
	{
		return userName;
	}
	
	public void setUserName(String username)
	{
		this.userName = username;
	}
	
	public long getId()
	{
		return ID;
	}
	
	public void setId(long iD)
	{
		ID = iD;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
}
