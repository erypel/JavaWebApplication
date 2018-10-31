package com.javawebapp.service;

import org.springframework.stereotype.Component;

import com.javawebapp.dao.impl.UserDaoImpl;
import com.javawebapp.model.User;

@Component
public class UserService
{
	UserDaoImpl userDao = new UserDaoImpl();
	
	public User getUser(String username, String password)
	{
		User user = userDao.getUser(username, password);
		return user;
	}
	
	public void register(User user)
	{
		userDao.insertUser(user.getUserName(), user.getPassword(), user.getEmail());
	}
	
}
