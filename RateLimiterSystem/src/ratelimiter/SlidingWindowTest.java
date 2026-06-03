package ratelimiter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class SlidingWindowTest {
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Test
	void testCanQueue() {
		fail("Not yet implemented");
	}

	@Test
	void testEnqueue() throws InterruptedException {
		SlidingWindow sw = new SlidingWindow(5, 10000);
		logger.info("\n\n----- Part 1 -----");
		for(int i=0; i<10; i++) {
			Date requestTimestamp = new Date();
			logger.info(String.format(
					"Request %d: <%d:%d:%d> ==> Queued: %s", 
					i, requestTimestamp.getHours(),
					requestTimestamp.getMinutes(),
					requestTimestamp.getSeconds(),
					sw.enqueue(requestTimestamp)));
			;
			Thread.currentThread().sleep(4000);
		}
		logger.info("Window size (part-1): " + sw.size());
		logger.info("Window (part-1): " + sw);
		
		Thread.currentThread().sleep(1000);
		logger.info("\n\n----- Part 2 -----");
		for(int i=0; i<10; i++) {
			Date requestTimestamp = new Date();
			logger.info(String.format(
					"Request-2 %d: <%d:%d:%d> ==> Queued: %s", 
					i, requestTimestamp.getHours(),
					requestTimestamp.getMinutes(),
					requestTimestamp.getSeconds(),
					sw.enqueue(requestTimestamp)));
			Thread.currentThread().sleep(1000);
		}
		
		logger.info("Window size (part-2): " + sw.size());
		logger.info("Window (part-2): " + sw);
	}

	@Test
	void testSize() throws InterruptedException {
		SlidingWindow sw = new SlidingWindow(5, 10000);
		sw.enqueue(new Date(System.currentTimeMillis()));
		Thread.currentThread().sleep(1000);
		sw.enqueue(new Date(System.currentTimeMillis()));
		Thread.currentThread().sleep(1000);
		sw.enqueue(new Date(System.currentTimeMillis()));
		logger.info("Window size: " + sw.size());
		Assert.assertEquals(3, sw.size());
		logger.info("Window: " + sw);
	}

}
