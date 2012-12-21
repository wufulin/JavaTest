package com.wufulin.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	static{
		try {
			sessionFactory = new Configuration().configure("hsqldb.cfg.xml")
					.buildSessionFactory();
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		// Alternatively,you could look up in JNDI here
		return sessionFactory;
	}
	
	public static void shutdown(){
		// Close caches and connection pools
		getSessionFactory().close();
	}
}
