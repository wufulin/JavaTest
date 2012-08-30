package com.wufulin.jdbc;

import java.lang.reflect.Constructor;

import com.wfl.domain.User;

public class ReflectTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		User user=(User)create(User.class);
		System.out.println(user);
	}

	static Object create(Class clazz) throws Exception{
		Constructor con=clazz.getConstructor(String.class);
		Object obj=con.newInstance("wufulin");
		return obj;
	}
}
