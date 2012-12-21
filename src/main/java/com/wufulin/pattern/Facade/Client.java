package com.wufulin.pattern.Facade;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LetterProcess letterProcess = new LetterProcessImpl();
		
		letterProcess.writeContext("Hello,It's me,do you know who I am? I'm your old lover.I'd like to ...");
		
		letterProcess.fillEnvelope("Happy Road No. 666,God Province,Heaven");
		
		letterProcess.letterIntoEnvelope();
		
		letterProcess.sendLetter();
	}

}
