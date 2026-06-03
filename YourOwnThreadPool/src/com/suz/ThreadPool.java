/**
 * 
 */
package com.suz;

import java.util.List;

/**
 * 
 */
public interface ThreadPool {
	void start() throws ThreadPoolException;
	
	void shutdown() throws ThreadPoolException;
	
	void submit(List<Runnable> r) throws ThreadPoolException;
}
