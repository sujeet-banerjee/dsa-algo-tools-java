package com.suz.concurrency.trials;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is improved / effective version of:
 * @see #ConcurrentLinkedQueueIneffectiveTests
 */
public class ConcurrentLinkedQueueEffectiveTests {
	
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
					try { // try?? So that we know if the monitor-state exceptions are not gobbled
						// by the executor service
						
						// stale code for ref. This one should be moved within the sync block
						// so it makes business sense!
						q.offer(al.addAndGet(10));
						
						/*
						 * BLUNDER!!!
						 * If you call notify/notifyAll outside the Synch block (without lock),
						 * You will get blown up:
						 * 
						 * '''
						 * java.lang.IllegalMonitorStateException: current thread is not owner
							at java.base/java.lang.Object.notifyAll(Native Method)
						 * '''
						 */
						//q.notifyAll();
						
						synchronized(q) {
							// business logic, to manage/mutate quque or data:
							q.offer(al.addAndGet(10));
							
							q.notifyAll();
						}
						
						
						System.out.println("Queue peek: "+ q.peek());
						System.out.println("Queue size: "+ q.size());
					} catch(Exception e) {
						e.printStackTrace();
					}
					return;
				}, 3000, 3000, TimeUnit.MILLISECONDS);
		
		System.out.println("DONE with producers...!!!");
	}
	
	public static void createConsumers(Queue<Long> q) {
		System.out.println("---- CREATING CONSUMERS ----");
		for(int i=0; i<5; i++) {
			System.out.println("CREATING Consumer Th: "+ i);
			new Thread(
					() -> {
						try {
							System.out.println("RUNNING Consumer Th: ");
							while(true /* or not shutdown */) {
								
								// If Shutdown
								// return;
							
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
									Long val = null;
									synchronized(q) {
										while((val=q.poll()) == null) {
											try { // Try?? Needed by the wait()
												q.wait();
											} catch (InterruptedException e) {
												// if Shutdown 
												// then return
												e.printStackTrace();
											}
										}
									}
									
									// - do your business logic here (outside the sync block) -
									System.out.println("Consumer:: got val: "+ val);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					// BLUNDER!!! Do not call thread's run!!
						// CALL start() !!
					}).start();
		}
		
		System.out.println("DONE! creating consumers!!!");
	}

}
