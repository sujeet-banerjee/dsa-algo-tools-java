package com.suz.thp2;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.ArrayList;

public class BasicThreadPool implements ThreadPool {
	
	private final int qSize;
	private final int workerSize;
	
	/**
	 * BLUNDER1: MISSED this (required to block new 
	 * submission when already shutdown)
	 */
	private boolean isShutdown = false;
	
	private List<Worker> workers = new ArrayList<>();
	private Queue<Runnable> taskQueue;

	/**
	 * BLUNDER3: Missed the ease-of-use: th-pool-NAME!!!
	 * @param qSize
	 * @param workerSize
	 */
	public BasicThreadPool(int qSize, int workerSize) {
		this.qSize = qSize;
		this.workerSize = workerSize;
		
		init();
	}
	
	protected void init() {
		this.taskQueue = new LinkedBlockingQueue<>(this.qSize);
		for(int i=0; i<workerSize; i++) {
			this.workers.add(new Worker(this.taskQueue));
		}
	}
	
	public void start() throws Exception {
		for(Worker w: this.workers) {
			w.start();
		}
	}
	
	public void submit(Runnable task) throws Exception {
		if(!isShutdown) {
			synchronized(this.taskQueue) {
				if(this.taskQueue.size() < this.qSize) {
					this.taskQueue.offer(task);
					this.taskQueue.notifyAll();
				}
			}
		} else {
			throw new IllegalStateException("The thread-pool is already shudown!");
		}
	}
	
	/**
	 * BLUNDER2: did not put clearing of the queue in 
	 * 'synchronized' block!!!
	 */
	public void shutdown() throws Exception {
		// Empty Task queue
		synchronized(this.taskQueue) {
			this.isShutdown = true;
			this.taskQueue.removeAll(taskQueue);
		}
		
		// Shutdown all workers
		for(Worker w: workers) {
			w.shutdown();
			w.interrupt();
		}
	}
}
