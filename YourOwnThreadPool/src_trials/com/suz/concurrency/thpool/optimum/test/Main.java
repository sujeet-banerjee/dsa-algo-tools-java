package com.suz.concurrency.thpool.optimum.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.suz.concurrency.thpool.optimum.SimpleThreadPool;
import com.suz.concurrency.thpool.optimum.ThreadPool;
import com.suz.concurrency.thpool.optimum.ThreadPoolException;
import com.suz.test.FibbonaciTask;

public class Main {
	public static void main(String[] args) throws ThreadPoolException, InterruptedException {
		ThreadPool thp = new SimpleThreadPool("UnitTest", 8, 10000);
		thp.start();
		
		List<Runnable> taskList = new ArrayList<>();
		taskList.add(new FibbonaciTask("Fibb-48", 48));
		taskList.add(new FibbonaciTask("Fibb-30", 30));
		
		for(Runnable t: taskList) {
			thp.submit(t);
		}
		
		Thread.sleep(10000);
		final AtomicLong al = new AtomicLong(10000);
		for(int i=0; i<40; i++) {
			Long val = al.addAndGet(10L);
			thp.submit(() -> {
				 String msg = String.format(" [th-%s] GOBBLE [NUM(%d)] ",
			                Thread.currentThread().getName(), val
			                );
			     System.out.println(msg);
			     // Introduces delayed processing
			     new FibbonaciTask("Rand-"+val, 42).run();
			});
		}
		
		Thread.sleep(10000);
		try {
			thp.shutdown();
			thp.submit(taskList.get(0));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
	}
}
