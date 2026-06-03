package com.suz.concurrency.trials;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentLinkedQueueIneffectiveTests {
	
	public static void main(String[] args) {
		// Create queue
		final Queue<Long> q = new ConcurrentLinkedQueue<>();
		
		// Create consumers - 5 threads (non-reentrant)
		createConsumers(q);
		
		// Delay
		delay(10000);
		
		
		// create scheduled producer threads
		createScheduledProducers(q);
	}
	
	public static void delay(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void createScheduledProducers(final Queue<Long> q) {
		System.out.println("---- CREATING PRODUCERS ----");
		ScheduledExecutorService svc = Executors.newScheduledThreadPool(10);
		AtomicLong al = new AtomicLong(10000);
		svc.scheduleAtFixedRate(
				() -> {
					q.offer(al.addAndGet(10));
					System.out.println("Queue peek: "+ q.peek());
					System.out.println("Queue size: "+ q.size());
					return;
				}, 3000, 3000, TimeUnit.MILLISECONDS);
		
		System.out.println("DONE with producers...!!!");
	}
	
	public static void createConsumers(Queue<Long> q) {
		System.out.println("---- CREATING CONSUMERS ----");
		for(int i=0; i<5; i++) {
			new Thread(
					() -> {
						if(q instanceof LinkedBlockingQueue) {
							try {
								long val = ((LinkedBlockingQueue<Long>)q).poll(
										200000, TimeUnit.HOURS);
								System.out.println("Consumer :: val= "+ val);
							} catch (InterruptedException e) {
								e.printStackTrace(System.out);
							}
						} 
						/* - No need -
						else if(q instanceof ConcurrentLinkedQueue) {
							 Long val = ((ConcurrentLinkedQueue<Long>)q).poll();
							 System.out.println("Consumer:: got val: "+ val);
						} 
						*/
						else {
							Long val = q.poll();
							System.out.println("Consumer:: got val: "+ val);
						}
						return;
					}).run();
		}
		
		System.out.println("DONE! creating consumers!!!");
	}

}
