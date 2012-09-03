package com.wufulin.jdbc.service;

import com.wufulin.jdbc.entity.*;
import com.wufulin.jdbc.dao.UserDao;

public class UserService {
	private UserDao userDao;
	
	public void regist(User user){
		userDao.addUser(user);
	}
}
