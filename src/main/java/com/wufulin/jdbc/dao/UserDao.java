package com.wufulin.jdbc.dao;

import com.wufulin.jdbc.entity.*;

public interface UserDao {
	
	public void addUser(User user);
	
	public void delete(User user);
	
	public void update(User user);
	
	public User findUser(String loginName,String password);
	
	public User getUser(int userId);
}
