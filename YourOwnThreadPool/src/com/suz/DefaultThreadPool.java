package com.suz;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class DefaultThreadPool implements ThreadPool {
	
	private final int poolSize;
	private final int qSize;
	private final String name;
	private final Queue<Runnable> q;
	private final List<Worker> workers;
	private boolean isShutdown = false;
	
	public DefaultThreadPool(String name, int poolSize, int qSize) {
		this.name = name;
		this.poolSize = poolSize;
		this.qSize = qSize;
		this.q = new LinkedBlockingQueue<>(this.qSize);
		this.workers = new ArrayList<>(this.poolSize);
		
		for(int i=0; i<this.poolSize; i++) {
			this.workers.add(new Worker(
					String.format("ThPool-%s-%d", this.name, i), 
					this.q));
		}
	}
	
	public void start() throws ThreadPoolException {
		for(Worker w: this.workers) {
			w.start();
		}
	}
	
	public void shutdown() throws ThreadPoolException{
		System.out.println("Shutting down thread pool: "+ this.name);
		synchronized(this.q) {
			this.isShutdown = true;
			
			// should clean up (preempt) the unattended tasks in the 
			// queue! Or may be configurable for non-preemption!
		}
		
		for(Worker w: this.workers) {
			w.shutdown();
			q.notify();
			w.interrupt();
		}
		
		
	}
	
	public void submit(List<Runnable> r) throws ThreadPoolException {
		synchronized(this.q) {
			if(this.isShutdown) {
				throw new ThreadPoolException("Can't accept new tasks; "
						+ "Thread pool Already shutdown!");
			}
			this.q.addAll((Collection<? extends Runnable>) r);
			this.q.notify();
		}
	}
}
