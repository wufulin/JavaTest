package com.wufulin.pattern.Command;

public class Client {

	public static void main(String[] args){
		Invoker invoker=new Invoker();
		Command command=new DeletePageCommand();
		invoker.setCommand(command);
		invoker.action();
	}
}
