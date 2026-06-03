package com.suz.concurrency.trials;

import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class PriorityBlockingQueueTests {

	public PriorityBlockingQueueTests() {
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		final PriorityBlockingQueue<Long> q = new PriorityBlockingQueue<Long>(
				10,
				(x, y) -> Long.compare(x, y)
				) {
			// Nothing to override yet!
		};
		
		q.offer(100L);
		q.offer(99L);
		q.offer(10L);
		q.offer(1000L);
		
		System.out.println(q);
		System.out.println(q.peek());
		System.out.println(q.poll(100, TimeUnit.MINUTES));
		System.out.println(q.peek());
		

		AtomicLong at = new AtomicLong(10000);
		ScheduledExecutorService svc = Executors.newScheduledThreadPool(10);
		svc.scheduleWithFixedDelay( () -> {
					q.offer(at.addAndGet(10));
					q.offer(at.addAndGet(10));
				}, 
				3000, 3000, TimeUnit.MILLISECONDS);
		
		System.out.println(q);
		
		// Test blocking
		for(int i=0; i<10; i++) {
			System.out.println("["+ i+ "] Fetched: "+ q.take());
		}
	}
	
}
