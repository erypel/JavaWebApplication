package com.javawebapp.dao;

import java.util.List;

import com.javawebapp.objects.User;

public interface UserDao {
	public List<User> getAllUsers();

	public User getUser(long id);

	public User getUser(String userName);

	public User getUser(String userName, String password);

	public void insertUser(String username, String password, String email, Long id);

	public void insertUser(String username, String password, String email);

	public void updateUsername(String oldUserName, String newUserName);

	public void updateUsername(long id, String newUserName);

	public void updateUserPassword(String userName, String newPassword);

	public void updateUserPassword(long id, String newPassword);

	public void deleteUser(String userName);

	public void deleteUser(long id);
}
