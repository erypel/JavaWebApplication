package com.javawebapp.dao;

import java.util.List;

import com.javawebapp.objects.User;

public interface UserDao {
	//TODO potential security flaw?
	public List<User> getAllUsers();
	public User getUser(long id);
	public User getUser(String username);
	public void updateUser(String username);
	public void updateUser(long id);
	public void deleteUser(String username);
	public void deleteUser(long id);
}
