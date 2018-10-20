package com.javawebapp.dao;

import java.util.List;

import com.javawebapp.objects.User;

public interface UserDao {
	// get methods

	public List<User> getAllUsers();

	public User getUser(long id);

	public User getUser(String username);

	public User getUser(String username, String password);

	// delete methods

	public void deleteUser(long id);

	public void deleteUser(String username);

	// update methods

	public void updateUsername(long id, String newUsername);

	public void updateUserPassword(long id, String newPassword);

	public void updateUsername(String oldUsername, String newUsername);

	public void updateUserPassword(String username, String newPassword);

	// insert methods

	public void insertUser(String username, String pwd, String email);

	public void insertUser(String username, String password, String email, Long id);
}
