package com.suz.concurrency.thpool.optimum;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * USE: 
 * PriorityBlockingQueue
 */
public class PriorityThreadPool extends SimpleThreadPool {

	public PriorityThreadPool(String name, int poolSize, int queueSize) {
		super(name, poolSize, queueSize);
		// TODO Auto-generated constructor stub
	}

	public PriorityThreadPool() {
		// TODO Auto-generated constructor stub
	}

}
