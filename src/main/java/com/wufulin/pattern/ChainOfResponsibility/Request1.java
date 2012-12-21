package com.wufulin.pattern.ChainOfResponsibility;

class Request1 extends AbstractRequest {

	public Request1(String content) {
		super(content);
	}

	@Override
	public int getRequestLevel() {
		return Levels.LEVEL_01;
	}

}

class Request2 extends AbstractRequest{

	public Request2(String content) {
		super(content);
	}

	@Override
	public int getRequestLevel() {
		return Levels.LEVEL_02;
	}
	
}

class Request3 extends AbstractRequest{

	public Request3(String content) {
		super(content);
	}

	@Override
	public int getRequestLevel() {
		return Levels.LEVEL_03;
	}
	
}

class Request4 extends AbstractRequest{

	public Request4(String content) {
		super(content);
	}

	@Override
	public int getRequestLevel() {
		return Levels.LEVEL_04;
	}
	
}