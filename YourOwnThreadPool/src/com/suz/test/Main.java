package com.suz.test;

import java.util.ArrayList;
import java.util.List;

import com.suz.DefaultThreadPool;
import com.suz.ThreadPool;
import com.suz.ThreadPoolException;

public class Main {
	public static void main(String[] args) throws ThreadPoolException, InterruptedException {
		ThreadPool thp = new DefaultThreadPool("UnitTest", 5, 20);
		thp.start();
		
		List<Runnable> taskList = new ArrayList<>();
		taskList.add(new FibbonaciTask("Fibb-46", 46));
		taskList.add(new FibbonaciTask("Fibb-30", 30));
		thp.submit(taskList);
		
		Thread.sleep(10000);
		thp.shutdown();
		thp.submit(taskList);
	}
}
