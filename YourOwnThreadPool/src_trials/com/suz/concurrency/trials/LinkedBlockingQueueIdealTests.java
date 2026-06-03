package com.suz.concurrency.trials;

import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueIdealTests {
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
		final AtomicLong al = new AtomicLong(10000L);
		svc.scheduleAtFixedRate(
				() -> {
					/*
					 * At this rate the queue will be emptied almost
					 * the moment this value is produced!!!
					 * ==> Leading to q.size as zero, almost always!
					 * 
					 * And the hungry wolves will not pounce on every offer!
					 * (thundering heard)
					 */
					q.offer(al.addAndGet(10L));
					
					
					// Enable this to flood the queue! 
					// And offer the wolves (consumers) more than they can gobble!
					for(int i=0; i<40; i++) {
						q.offer(al.addAndGet(10L));
					}
					
					/*
					 * - - NOT NEEDED - - 
					 * Has no effect, because, the BlockingQueue takes care of that
					 * 
					 * 
					synchronized(q) {
						q.notifyAll();
					}
					*/
					
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
				
				while(true /* Or not-shutdown */) {
					Long val = null;
					if(q instanceof LinkedBlockingQueue) {
						try {
							// block (forever)!
							val = ((LinkedBlockingQueue<Long>)q).poll(
									Long.MAX_VALUE, TimeUnit.DAYS);
						} catch (InterruptedException e) {
							e.printStackTrace(System.out);
							
							// If shutdown issued,
							// return!
						}
					} else {
						
						// shouldn't come here, BTW!!
						System.out.println("NOPE!!!");
						
						// Normal Queue, will end this thread, the returned 
						// val will be null, as there is no blocking/waiting for 
						// an item to be present, and this poll is NON-BLOCKING!!
						val = q.poll();
						
					    /*
					     * If you want to make this work as well,
					     * you need to simulate BLOCKING wait yourself:
					     * @see: #ConcurrentLinkedQueueEffectiveTests
					     */
					}
					
					// - - Your business logic here! - -
					System.out.println("Consumer :: val= "+ val);
				}
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
