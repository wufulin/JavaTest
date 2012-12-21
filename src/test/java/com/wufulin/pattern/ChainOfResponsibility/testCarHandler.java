package com.wufulin.pattern.ChainOfResponsibility;

public class testCarHandler {

	public static void main(String[] args){
		CarHandler head=new CarHeadHandler();
		CarHandler body=new CarBodyHandler();
		CarHandler tail=new CarTailHandler();
		
		head.setNextHandler(body).setNextHandler(tail);
		
		head.HandlerCar();
	}
}
