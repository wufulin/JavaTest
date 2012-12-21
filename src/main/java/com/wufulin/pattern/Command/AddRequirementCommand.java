package com.wufulin.pattern.Command;

public class AddRequirementCommand extends Command {

	@Override
	public void execute() {
		super.requirement.find();
		super.requirement.add();
		super.requirement.plan();
	}

}
