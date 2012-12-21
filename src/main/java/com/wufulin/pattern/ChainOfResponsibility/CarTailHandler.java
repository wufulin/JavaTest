package com.wufulin.pattern.ChainOfResponsibility;

public class CarTailHandler extends CarHandler {

	@Override
	public void HandlerCar() {
		System.out.println("组装车尾");
		if(this.carHandler!=null){
			this.carHandler.HandlerCar();
		}
	}

}
