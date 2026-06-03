package com.suz.thp2;

import java.util.Queue;

public class Worker extends Thread {
	
	private Queue<Runnable> taskQueue;
	private boolean isShutDown;

	Worker(Queue<Runnable> taskQueue) {
		this.taskQueue = taskQueue;
	}
	
	public void shutdown() {
		this.isShutDown = true;
	}
	
	@Override
	public void run() {
		// Wait notify logic
		while(true) {
			Runnable task = null;
			synchronized(this.taskQueue) {
				while(this.taskQueue.isEmpty()) {
					try {
						this.taskQueue.wait();
					} catch (InterruptedException e) {
						if(isShutDown) {
							return;
						}
						// Else keep looking for work
					}
				}
				
				// You can abstract out this method as
				// abstract Task getNextTask();
				task = this.taskQueue.poll();
			}
			
			try {
				
				/*
				 *  Note, if the thread.interrupt was called by external
				 *  forces, nothing happens here, except that
				 *  this.isInterrupted() returns true.
				 */
				
				task.run();
			} catch (Exception e) {
				// Log thread errors
				System.out.println("Exception executing thread:" + e);
				e.printStackTrace(System.out);
			}
		}
	}
}
