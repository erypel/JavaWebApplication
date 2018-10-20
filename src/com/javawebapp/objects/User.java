package com.javawebapp.objects;

import java.io.Serializable;

import javax.persistence.*;

import com.javawebapp.util.JavaWebAppUtils;

@Entity
@Table(name="USER")
public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="USERNAME")
	private String userName;
	
	@Id
	@Column(name="ID", updatable = false, nullable = false)
	private long ID;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	public User() {
		
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String username, String email, String password) 
	{
		this.userName = username;
		this.ID = JavaWebAppUtils.generateUniqueId();
		this.email = email;
		this.password = password;
	}
	
	public User(String username, String email, String password, Long id) 
	{
		this.userName = username;
		this.ID = id;
		this.email = email;
		this.password = password;
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
