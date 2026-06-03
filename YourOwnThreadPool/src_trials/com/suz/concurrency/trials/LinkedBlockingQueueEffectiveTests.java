package com.suz.concurrency.trials;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueEffectiveTests {
	public static void main(String [] args) {
		//newScheduledThreadPool
		
		Queue<Long> q = getQueue();
		
		startConsumerThreads(q);
		
		delay();
		
		startProducers(q);
		
	}

	private static void startProducers(Queue<Long> q) {
		System.out.println("---- CREATING PRODUCERS ----");
		ScheduledExecutorService svc = Executors.newScheduledThreadPool(10);
		final AtomicLong al = new AtomicLong(0L);
		svc.scheduleAtFixedRate(
				() -> {
					q.offer(1000L + al.addAndGet(10L));
					System.out.println("PEEK: "+ q.peek());
					System.out.println("Q Size: "+ q.size());
					return;
				},
				3000,
				3000,
				TimeUnit.MILLISECONDS
		);
		System.out.println("DONE with producers...!!!");
	}

	private static void delay() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The consumer threads are blocked until they are able to 
	 * poll and consume an item (each) from the Q. The threads
	 * exit after executing once.
	 *  
	 * @param q
	 */
	public static void startConsumerThreads(Queue<Long> q) {
		System.out.println("---- CREATING CONSUMERS ----");
		for(int i=0; i<5; i++) {
			new Thread(() -> {
				if(q instanceof LinkedBlockingQueue) {
					try {
						long val = ((LinkedBlockingQueue<Long>)q).poll(
								200000, TimeUnit.HOURS);
						System.out.println("Consumer :: val= "+ val);
					} catch (InterruptedException e) {
						e.printStackTrace(System.out);
					}
				} else {
					q.poll();
				}
				return;
			}).start(); 
		}
		
		System.out.println(
				"=== CONSUMER THREADS STARTED, "
				+ "AND THEY ARE BLOCKED ON POLL ON THE QUEUE ==");
	}
	
	public static Queue<Long> getQueue() {
		return new LinkedBlockingQueue<>();
	}
}
