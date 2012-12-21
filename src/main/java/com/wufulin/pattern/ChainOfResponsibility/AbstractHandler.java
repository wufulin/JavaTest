package com.wufulin.pattern.ChainOfResponsibility;

abstract class AbstractHandler {

	protected AbstractHandler nextHandler = null;
	
	public final void handleRequest(AbstractRequest request){
		if(this.getHandlerLevel()==request.getRequestLevel()){
			this.handle(request);
		}else{
			if(this.nextHandler != null){
				System.out.println("当前 处理者 - "+this.getHandlerLevel()+" 不足以处理 请求 - "+request.getRequestLevel());
				this.nextHandler.handleRequest(request);
			}else{
				System.out.println("当前 处理者 - "+this.getHandlerLevel()+" 不足以处理 请求 - "+request.getRequestLevel());
				System.out.println("职责链上的所有处理者都不能胜任该请求...");
			}
		}
	}
	
	public AbstractHandler setNextHandler(AbstractHandler nextHandler){
		this.nextHandler = nextHandler;
		return this.nextHandler;
	}
	
	protected abstract int getHandlerLevel();
	
	protected abstract void handle(AbstractRequest request);
}
