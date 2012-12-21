package com.wufulin.pattern.Command;

public class DeletePageCommand extends Command {

	@Override
	public void execute() {
		super.page.find();
		super.page.delete();
		super.page.plan();
	}

}
