/**
 * 
 */
package com.suz.concurrency.thpool.optimum;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 */
class SimpleWorker extends Thread {
	
	// FIXME replace with the owner (th-pool)
	private SimpleThreadPool thPool;
	
	private boolean isShutdown;

	/**
	 * @param name
	 */
	public SimpleWorker(String name, SimpleThreadPool thPool) {
		super(name);
		this.thPool = thPool;
	}
	
	protected void shutdown() {
		this.isShutdown = true;
	}
	
	@Override
	public void run() {
		while(!isShutdown) {
			Runnable task;
			try {
				task = thPool.getNextTask();
				task.run();
			} catch (InterruptedException e) {
				if(isShutdown) {
					// We are done for this thread execution!
					System.out.println("Thread has shutdown: "+ this.getName());
					return;
				} else {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Thread has shutdown: "+ this.getName());
	}
}
