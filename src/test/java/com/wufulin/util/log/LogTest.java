package com.wufulin.util.log;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Before;
import org.junit.Test;

public class LogTest {
	
	private Logger logger;
	
	@Before
	public void initialize(){
		logger=Logger.getLogger("logger");
		logger.removeAllAppenders();
		Logger.getRootLogger().removeAllAppenders();
	}
	
	@Test
	public void testBasicLogger(){
		BasicConfigurator.configure();
		logger.info("basicLogger");
	}

	@Test
	public void testLogAddAppenderWithStream(){
		logger.addAppender(new ConsoleAppender(
				new PatternLayout("%p %t %m%n"), 
				ConsoleAppender.SYSTEM_OUT));
		logger.info("testLogAddAppenderWithStream");
	}
	
	@Test
	public void testLogAddAppenderWithoutStream(){
		logger.addAppender(new ConsoleAppender(
				new PatternLayout("%p %t %m%n")));
		logger.info("testLogAddAppenderWithoutStream");
	}
	
	@Test
	public void testLogAddAppenderWithoutLayout(){
		logger.addAppender(new ConsoleAppender());
		logger.info("testLogAddAppenderWithoutLayout");
	}
}
