package com.wufulin.thread.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithSyncUsingLock {

	private static Account account = new Account();

	private static class AddAPennyTask implements Runnable {

		public void run() {
			account.deposit(1);
		}

	}

	// An inner class for account
	private static class Account {

		// Create a lock
		private static Lock lock = new ReentrantLock();
		private int balance = 0;

		public int getBalance() {
			return balance;
		}

		public void deposit(int amount) {

			// Acquire the lock
			lock.lock();

			try {
				int newBalance = balance + amount;

				// This delay is deliberately added to magnify the
				// data-corruption problem and make it easy to see
				Thread.sleep(5);
				
				balance=newBalance;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				lock.unlock();
			}

		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ExecutorService executor = Executors.newCachedThreadPool();

		// Create and launch 100 threads
		for (int i = 0; i < 100; i++) {
			executor.execute(new AddAPennyTask());
		}

		executor.shutdown();

		// Wait untill all tasks are finished
		while (!executor.isTerminated()) {

		}

		System.out.println("What is balance? " + account.getBalance());
	}

}
