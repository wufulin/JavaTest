package com.wufulin.orm;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.wufulin.orm.entity.Message;
import com.wufulin.orm.entity.SMSMessage;
import com.wufulin.util.HibernateUtil;

public class HelloMessage {

	public void testHibernate() throws Exception{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		SMSMessage message = new SMSMessage("Hello World");
		Long msgId = (Long) session.save(message);
		
		tx.commit();
		session.close();
		
		Session newSession = HibernateUtil.getSessionFactory().openSession();
		Transaction newTx = newSession.beginTransaction();
		
		List messages = newSession.createQuery("from SMSMessage m order by m.text asc").list();
		System.out.println(messages.size()+" message(s) found:");
		for(Iterator iter = messages.iterator();iter.hasNext();){
			SMSMessage loadedMsg = (SMSMessage) iter.next();
			System.out.println(loadedMsg.getText());
		}
		
		newTx.commit();
		newSession.close();
		
		HibernateUtil.shutdown();
	}

	@Test
	public void testAnnotation() throws Exception{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Message message = new Message("Hello World!!!");
		Long msgId = (Long) session.save(message);
		
		List messages = session.createQuery("from Message m order by m.text asc").list();
		System.out.println(messages.size()+" message(s) found:");
		for(Iterator iter = messages.iterator();iter.hasNext();){
			Message loadedMsg = (Message) iter.next();
			System.out.println(loadedMsg.getText());
		}
		
		tx.commit();
		session.close();
		HibernateUtil.shutdown();
	}
	
	public void testEntityManager() throws Exception{
	}
}
