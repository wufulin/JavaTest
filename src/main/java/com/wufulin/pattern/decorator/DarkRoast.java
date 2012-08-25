package com.wufulin.pattern.decorator;

public class DarkRoast extends Beverage {

	public DarkRoast(){
		description="DarkRoast Coffee";
	}
	
	@Override
	public double cost() {
		return .99;
	}

}
