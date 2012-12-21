package com.wufulin.pattern.State;

public class ClosingState extends LiftState {

	@Override
	public void open() {
		super.context.setLiftState(Context.openningState);
		super.context.getLiftState().open();
	}

	@Override
	public void close() {
		System.out.println("closing");
	}

	@Override
	public void run() {
		super.context.setLiftState(Context.runningState);
		super.context.getLiftState().run();
	}

	@Override
	public void stop() {
		super.context.setLiftState(Context.stoppingState);
		super.context.getLiftState().stop();
	}

}
