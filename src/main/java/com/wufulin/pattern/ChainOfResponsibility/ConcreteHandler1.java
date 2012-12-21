package com.wufulin.pattern.ChainOfResponsibility;

class ConcreteHandler1 extends AbstractHandler{

	@Override
	protected int getHandlerLevel() {
		return Levels.LEVEL_01;
	}

	@Override
	protected void handle(AbstractRequest request) {
		System.out.println("处理者-01 处理 " + request.getContent() + "\n");  
	}

}

class ConcreteHandler2 extends AbstractHandler{

	@Override
	protected int getHandlerLevel() {
		return Levels.LEVEL_02;
	}

	@Override
	protected void handle(AbstractRequest request) {
		System.out.println("处理者-02 处理 " + request.getContent()+ "\n");  
	}
	
}

class ConcreteHandler3 extends AbstractHandler{

	@Override
	protected int getHandlerLevel() {
		return Levels.LEVEL_03;
	}

	@Override
	protected void handle(AbstractRequest request) {
		 System.out.println("处理者-03 处理 " + request.getContent()+ "\n");  
	}
	
}