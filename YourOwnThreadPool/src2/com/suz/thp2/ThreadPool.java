package com.suz.thp2;

public interface ThreadPool {
	/**
	 * TODO check specific exception
	 * @throws Exception
	 */
	public void start() throws Exception;
	
	public void submit(Runnable task) throws Exception;
	
	public void shutdown() throws Exception;
	
}
