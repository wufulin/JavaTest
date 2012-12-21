package com.wufulin.pattern.Facade;

/**
 * 定义一个写信的过程
 * @author wufulin
 */
public interface LetterProcess {

	/**
	 * 写信的内容
	 * @param context
	 */
	public void writeContext(String context);
	
	/**
	 * 写信封
	 * @param address
	 */
	public void fillEnvelope(String address);
	
	/**
	 * 把信放到信封里
	 */
	public void letterIntoEnvelope();
	
	/**
	 * 邮递
	 */
	public void sendLetter();
}
