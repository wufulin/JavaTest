package com.wufulin.pattern.State;

public class RunningState extends LiftState {

	@Override
	public void open() {
		
	}

	@Override
	public void close() {

	}

	@Override
	public void run() {
		System.out.println("running");
	}

	@Override
	public void stop() {
		super.context.setLiftState(Context.stoppingState);
		super.context.getLiftState().stop();
	}

}
