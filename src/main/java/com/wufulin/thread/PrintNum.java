package com.wufulin.thread;

class PrintNum implements Runnable{
	
	private int lastNum;
	
	public PrintNum(int n){
		this.lastNum=n;
	}

	public void run() {
		Thread thread4=new Thread(new PrintChar('c', 40));
		thread4.start();
		try {
			for(int i=0;i<=lastNum;i++){
				System.out.print(" "+i);
				//Thread.yield();
//				if(i>=50) Thread.sleep(10);
				if(i==50) thread4.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}