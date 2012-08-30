package com.wufulin.jdbc.service;

import com.wfl.domain.User;
import com.wfl.jdbc.dao.UserDao;

public class UserService {
	private UserDao userDao;
	
	public void regist(User user){
		userDao.addUser(user);
	}
}
