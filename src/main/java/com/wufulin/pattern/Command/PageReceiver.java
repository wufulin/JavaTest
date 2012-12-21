package com.wufulin.pattern.Command;

public class PageReceiver extends Receiver {

	@Override
	public void find() {
		System.out.println("find page group");
	}

	@Override
	public void delete() {
		System.out.println("delete one page");
	}

	@Override
	public void change() {
		System.out.println("change one page");
	}

	@Override
	public void plan() {
		System.out.println("plan one page");
	}

	@Override
	public void add() {
		System.out.println("add one page");
	}

}
