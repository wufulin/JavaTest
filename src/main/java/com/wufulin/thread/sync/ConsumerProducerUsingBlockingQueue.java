package com.wufulin.thread.sync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConsumerProducerUsingBlockingQueue {
	private static ArrayBlockingQueue<Integer> buffer =
			new ArrayBlockingQueue<Integer>(2);

	private static class ProducerTask implements Runnable {

		public void run() {
			try {
				int i = 1;
				while (true) {
					System.out.println("Producer writes " + i);
					// Add any value to the buffer
					buffer.put(i++);
					Thread.sleep((int) (Math.random() * 10000));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private static class ConsumerTask implements Runnable {

		public void run() {
			try {
				while (true) {
					System.out.println("\t\t\tConsumer reads " + buffer.take());
					// Put the thread to sleep
					Thread.sleep((int) (Math.random() * 10000));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		// Create a thread pool with two threads
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new ProducerTask());
		executor.execute(new ConsumerTask());

		executor.shutdown();

	}

}
