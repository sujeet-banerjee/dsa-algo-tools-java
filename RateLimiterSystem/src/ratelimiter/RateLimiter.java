/**
 * 
 */
package ratelimiter;

import java.util.Map;

/**
 * TBD
 */
public class RateLimiter /*implements */ {
	
	private Map<Integer, SlidingWindow> customerRequests;
	
	private static final Integer LIMIT = 100;
	
	boolean rateLimit(int customerId) {
		boolean ret = false;
		
		// TODO implements this
		
		return ret;
	}

}
