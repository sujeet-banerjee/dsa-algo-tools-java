/**
 * 
 */
package com.suz;

/**
 * 
 */
public interface Task<T> extends Runnable {
	
	@Override
	default void run() {
		
	}
	
	
	T execute() throws Exception;
}
