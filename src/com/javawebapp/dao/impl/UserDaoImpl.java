package com.javawebapp.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.javawebapp.dao.UserDao;
import com.javawebapp.db.ConnectionUtils;
import com.javawebapp.objects.User;

//TODO after finishing this write some tests!
public class UserDaoImpl implements UserDao{

	@Override
	public List<User> getAllUsers() {
		try {
			Connection connection = ConnectionUtils.getMyConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO make calls to the db
		
		return null;
	}

	@Override
	public User getUser(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		
	}
	
}
