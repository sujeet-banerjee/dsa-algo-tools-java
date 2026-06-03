/**
 * 
 */
package com.suz;

import java.util.Queue;
import java.util.logging.Logger;

/**
 * 
 */
class Worker extends Thread {
	private Queue<? extends Runnable> q;
	private boolean isShutdown = false;
	Worker(String name, Queue<? extends Runnable> q) {
		super(name);
		if(q==null) {
			throw new IllegalArgumentException("The q cannot be null.");
		}
		this.q = q;
	}

	public void shutdown() {
		this.isShutdown = true;
	}
	
	@Override
	public void run() {
		//Logger.getLogger(getName())
		System.out.println("Starting worker: "+ this.getName());
		
		OUTER:		
		//while(!this.isShutdown) {
		while(true) {
			Runnable task = null;
			synchronized(this.q) {
				while(q.isEmpty()) {
					try {
						System.out.println("["+this.getName()+"] "+ "Waiting for tasks...");
						q.wait();
					} catch (InterruptedException e) {
						System.out.println("["+this.getName()+"] "+ " Interrupted!");
						if(this.isShutdown && this.q.isEmpty()) {
							System.out.println("["+this.getName()+"] "+ " Shutting down worker!");
							
							// Should we 'return'instead?
							break OUTER;
						}
					}
				}
				
				task = q.poll();
			}
			
			try {
				System.out.println("["+this.getName()+"] "+ " Executing task: "+ task);
				task.run();
			} catch (Exception e) {
				// TODO do something.
				// TODO store the result into the Task
			}
		}
	}
	
}
