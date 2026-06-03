package com.suz.rl2;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Set;
import java.util.HashSet;

public class SlidingWindow {
	
	private final ConcurrentLinkedQueue<Long> q = 
			new ConcurrentLinkedQueue<>();
	private final int winSize;
	private final long interval;

	public SlidingWindow(int winSize, long interval) {
		this.winSize = winSize;
		this.interval = interval;
	}

	public boolean request() {
		
		Long curTime = System.currentTimeMillis();
		System.out.println("\nCurr Size: "+ this.q.size());
		
		// cleanup (evict)
		Set<Long> evicted = new HashSet<>();
		if(!this.q.isEmpty()) {
			Long last = this.q.peek();
			while(!this.q.isEmpty() && 
					(curTime-last) > this.interval) {
				last = this.q.poll();
				evicted.add(last);
			}
		}
		
		System.out.println("Eviected: "+ evicted);
		
		boolean canQueue = this.q.size() < winSize;
		
		// BLUNDER1 - do not forget to queue the current Request!!
		// BLUNDER2 - do not add to queue without checking "canQueue"!
		if(canQueue) {
			this.q.offer(curTime);
		}
		
		System.out.println("Updated Size: "+ this.q.size());
		return canQueue;
	}

}
