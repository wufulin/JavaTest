package com.wufulin.pattern.ChainOfResponsibility;

public class testConcreteHandler {

	public static void main(String[] args){
		
		AbstractHandler handler1 = new ConcreteHandler1();
		AbstractHandler handler2 = new ConcreteHandler2();
		AbstractHandler handler3 = new ConcreteHandler3();
		
		handler1.setNextHandler(handler2).setNextHandler(handler3);
		
		AbstractRequest request1 = new Request1("请求-01");
		AbstractRequest request2 = new Request2("请求-02");
		AbstractRequest request3 = new Request3("请求-03");
		AbstractRequest request4 = new Request4("请求-04");
		
		handler1.handleRequest(request1);
		handler1.handleRequest(request2);
		handler1.handleRequest(request3);
		handler1.handleRequest(request4);
	}
}
