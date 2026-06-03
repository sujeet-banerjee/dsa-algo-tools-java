package com.suz.concurrency.thpool.optimum;

public interface ThreadPool {
	void submit(Runnable r) throws ThreadPoolException;
	
	void shutdown() throws ThreadPoolException;
	
	void init(String name, int poolSize, int queueSize) throws ThreadPoolException;
	
	void start() throws ThreadPoolException;
}
