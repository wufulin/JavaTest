package com.wufulin.thread.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountWithoutSync {

	private static Account account=new Account();
	
	private static class AddAPennyTask implements Runnable{

		public void run() {
			account.deposit(1);
		}
		
	}
	
	// An inner class for account
	private static class Account{
		
		private int balance=0;
		
		public int getBalance(){
			return balance;
		}
		
		public void deposit(int amount){
			int newBalance=balance+amount;
			
			// This delay is deliberately added to magnify the 
			// data-corruption problem and make it easy to see
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			balance=newBalance;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ExecutorService executor=Executors.newCachedThreadPool();
		
		// Create and launch 100 threads
		for(int i=0;i<100;i++){
			executor.execute(new AddAPennyTask());
		}
		
		executor.shutdown();
		
		// Wait untill all tasks are finished
		while(!executor.isTerminated()){
			
		}
		
		System.out.println("What is balance? "+account.getBalance());
	}

}
