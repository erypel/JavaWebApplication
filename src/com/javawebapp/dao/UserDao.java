package com.javawebapp.dao;

import java.sql.ResultSet;
import java.util.List;

import com.javawebapp.objects.User;

public interface UserDao {
	//TODO potential security flaw?
	public ResultSet getAllUsers();
	public User getUser(long id);
	public User getUser(String username);
	public void deleteUser(String username);
	public void deleteUser(long id);
	void updateUsername(String oldUsername, String newUsername);
	void updateUsername(long id, String newUsername);
	void updateUserPassword(String username, String newPassword);
	void updateUserPassword(long id, String newPassword);
}
