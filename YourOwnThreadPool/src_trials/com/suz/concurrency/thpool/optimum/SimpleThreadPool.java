/**
 * 
 */
package com.suz.concurrency.thpool.optimum;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * TODO SchedulerStrategy
 */
public class SimpleThreadPool implements ThreadPool {
	private String name;
	private int poolSize = 10;
	private boolean isShutdown;
	private int queueSize = 10000;
	
	private LinkedBlockingQueue<Runnable> q;
	
	private List<SimpleWorker> workers;
	
	public SimpleThreadPool(
			String name, 
			int poolSize,
			int queueSize
			/* TODO Strategy*/) {
		
		
		try {
			this.init(name, poolSize, queueSize);
		} catch (ThreadPoolException e) {
			e.printStackTrace();
		}
	}
	
	public void init(String name, int poolSize, int queueSize) throws ThreadPoolException {
		// TODO prevent changing this after start();
		this.poolSize = poolSize;
		this.name = name;
		this.queueSize = queueSize;
		
		// Init Queue (Future: should be based on Sched-Strategy)
		this.q = new LinkedBlockingQueue<>(this.queueSize);
		
		// Init workers
		this.workers = new ArrayList<>();
		for(int i=0; i<this.poolSize; i++) {
			String wName = String.format("%s--%d", this.name, i);
			this.workers.add(new SimpleWorker(wName, this));
		}
	}

	/**
	 * 
	 */
	public SimpleThreadPool() {
	}

	@Override
	public void submit(Runnable r) throws ThreadPoolException {
		synchronized(this) {
			if(isShutdown) {
				throw new ThreadPoolException("Thread pool has shut down!");
			}
		}
		
		// queue the runnable
		synchronized(this.q) {
			this.q.offer(r);
			
			// Not needed!
			//this.q.notifyAll();
		}
	}

	@Override
	public synchronized void shutdown() throws ThreadPoolException {
		this.isShutdown = true;
		
		for(SimpleWorker w: this.workers) {
			w.shutdown();
			w.interrupt();
		}
		
		for(SimpleWorker w: this.workers) {
			try {
				w.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new ThreadPoolException("Shutdown Interrupted", e);
			}
		}
		
		System.out.println("Tasks unexecuted: "+ this.q.size());
	}

	@Override
	public synchronized void start() throws ThreadPoolException {
		if(isShutdown) {
			throw new ThreadPoolException("Thread pool has shut down!");
		}
		
		// Start all the workers
		for(SimpleWorker w: this.workers) {
			w.start();
		}

	}

	/**
	 * Behavior of this method depends on the type of Queue chosen
	 * - If LinkedBlockingQueue, the simple impl, without checking null 
	 *   and wait-notify
	 * - If ConcurrentLinkedQueue (which does not support BLOCKING poll), 
	 *   you need to explicitly use while(null-check) and wait-notify
	 *   @see #ConcurrentLinkedQueueEffectiveTests
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	protected Runnable getNextTask() throws InterruptedException {
		//return this.q.poll(Long.MAX_VALUE, TimeUnit.DAYS);
		return this.q.take();
	}

}
