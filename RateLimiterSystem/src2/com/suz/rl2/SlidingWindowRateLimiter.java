package com.suz.rl2;

import java.util.Map;
import java.util.HashMap;

public class SlidingWindowRateLimiter implements RateLimiter {
	
	private final Map<String, SlidingWindow> map = new HashMap<>();
	private int winSize = WIN_SIZE;
	/**
	 * per second
	 */
	public static final int WIN_SIZE = 10;
	
	public SlidingWindowRateLimiter(int winSize) {
		this.winSize = winSize;
	}

	@Override
	public boolean request(String id) {
		synchronized(this.map) {
			// In millisecs
			long interval = 1000L;
			if(!this.map.containsKey(id)) {
				this.map.put(id, new SlidingWindow(winSize, interval));
			}
		}
		
		return map.get(id).request();
	}
}
