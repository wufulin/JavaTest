package com.wufulin.thread.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCooperation {

	private static Account account=new Account();
	
	// A task for adding an amount to the account
	public static class DepositTask implements Runnable{

		public void run() {
			try {
				while(true){
					account.deposit((int)(Math.random()*10)+1);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// A task for subtracting an amount from the account
	public static class WithdrawTask implements Runnable{
		public void run() {
			while(true){
				account.withdraw((int)(Math.random()*10+1));
			}
		}
	}
	
	// An inner class for account
 	private static class Account {
		// Create a new lock
		private static Lock lock = new ReentrantLock();

		// Create a condition
		private static Condition newDeposit = lock.newCondition();

		private int balance = 0;

		public int getBalance() {
			return balance;
		}

		public void withdraw(int amount) {
			lock.lock();
			try {
				while(balance<amount){
					System.out.println("\t\t\tWait for a deposit");
					newDeposit.await();
				}
				
				balance-=amount;
				System.out.println("\t\t\tWithdraw "+ amount+"\t\t"+getBalance());
			} 
			catch (InterruptedException ex) {
				ex.printStackTrace();
			} 
			finally {
				lock.unlock();
			}
		}
		
		public void deposit(int amount){
			lock.lock();
			try {
				balance += amount;
				System.out.println("Deposit " + amount + "\t\t\t\t\t"
						+ getBalance());
				newDeposit.signalAll();
			} 
			finally{
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		// Create a thread pool with two threads
		ExecutorService executor=Executors.newFixedThreadPool(2);
		executor.execute(new DepositTask());
		executor.execute(new WithdrawTask());
		executor.shutdown();
		
		System.out.println("Thread 1\t\tThread 2\t\tBalance");
	}
}
