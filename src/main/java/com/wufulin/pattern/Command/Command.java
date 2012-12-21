package com.wufulin.pattern.Command;

public abstract class Command {

	protected Receiver requirement=new RequirementReceiver();
	protected Receiver page=new PageReceiver();
	
	public abstract void execute();
}
