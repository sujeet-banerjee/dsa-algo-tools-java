package ratelimiter;

import java.util.Collections;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class SlidingWindow {
	Logger logger = Logger.getLogger(this.getClass().getName());
	private Queue<Date> window = new ConcurrentLinkedQueue<Date>();
	
	private int winSizeLimit;
	
	/**
	 * time interval in millis
	 */
	private long intervalInMillis;
	
	/**
	 * 
	 * @param winSizeLimit
	 * @param intervalInMillis
	 */
	public SlidingWindow(
			int winSizeLimit, 
			long intervalInMillis
			) {
		
		if(winSizeLimit<1) {
			throw new IllegalArgumentException(
					"Size mush be at least 1");
		}
		if(intervalInMillis<0) {
			throw new IllegalArgumentException(
					"The Sliding Window size cannot be < 0 millisecs");
		}
		
		this.winSizeLimit = winSizeLimit;
		this.intervalInMillis = intervalInMillis;
	}
	
	
	
	// Enqueue request
	public boolean canQueue(Date requestTimestamp) {
		if(requestTimestamp == null) {
			throw new IllegalArgumentException("The request TS cannot be null");
		}
		
		if(this.window.size() > 1) {
			final long windowStartTS = 
					System.currentTimeMillis() - this.intervalInMillis;
			logger.info("Before EVIC: "+ this.toString());
			this.window.removeIf(
					(Predicate<? super Date>) new Predicate<Date>() {

				@Override
				public boolean test(Date t) {
					return t.getTime() < windowStartTS;
				}
			});
			logger.info("After EVIC: "+ this.toString());
		}
		
		boolean canQueue = this.window.size() < this.winSizeLimit;
		return canQueue;
	}
	
	/**
	 * 
	 * @param requestTimestamp
	 * @return false if could not be enqueued.
	 */
	public boolean enqueue(Date requestTimestamp) {
		if (this.canQueue(requestTimestamp)) {
			this.window.add(requestTimestamp);
			return true;
		}
		
		return false;
	}
	
	// Dequeue (based on TS calculation)
	
	// Size
	public int size() {
		return this.window.size();
	}
	
	public String toString() {
		return this.window.stream().map(e -> 
				String.format("%d:%d:%d(%s)", 
						e.getHours(), 
						e.getMinutes(), 
						e.getSeconds(), e.getTime()) ).
				reduce("", (t, u) -> ""+t+ u+ ", ").
				toString();
	}

}
