package com.wufulin.pattern.Command;

public class RequirementReceiver extends Receiver {

	@Override
	public void find() {
		System.out.println("find the requirement group...");
	}

	@Override
	public void delete() {
		System.out.println("delete one requirement");
	}

	@Override
	public void change() {
		System.out.println("change one requirement");
	}

	@Override
	public void plan() {
		System.out.println("plan one requirement");
	}

	@Override
	public void add() {
		System.out.println("add one requirement");
	}

}
