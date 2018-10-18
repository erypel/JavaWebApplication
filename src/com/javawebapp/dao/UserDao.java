package com.javawebapp.dao;

import java.util.List;

import com.javawebapp.objects.User;

public interface UserDao {
	//TODO potential security flaw with passwords?
	public List<User> getAllUsers();
	public User getUser(long id);
	public User getUser(String username);
	public void deleteUser(String username);
	public void deleteUser(long id);
	public void updateUsername(String oldUsername, String newUsername);
	public void updateUsername(long id, String newUsername);
	public void updateUserPassword(String username, String newPassword);
	public void updateUserPassword(long id, String newPassword);
	public void insertUser(String username, String password, String email, Long id);
	public void insertUser(String username, String pwd, String email);
	public User getUser(String username, String password);
}
