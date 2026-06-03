package com.suz.rl2;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class SlidingWindowRateLimiterTest {
	
	@Test
	void testRequest2() {
		RateLimiter rt = new SlidingWindowRateLimiter(4);
		Map<Integer, Boolean> request = new HashMap<>(); 
		for(int i=0; i<3; i++) {
			request.put(i, rt.request("A"));
		}
		System.out.println("Request1: "+ request);
		
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			System.out.println("Exception: " + e);
		}
		
		for(int i=3; i<5; i++) {
			request.put(i, rt.request("A"));
		}
		
		System.out.println("Request2: "+ request);
		
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			System.out.println("Exception: " + e);
		}
		
		for(int i=5; i<7; i++) {
			request.put(i, rt.request("A"));
		}
		
		System.out.println("Request3: "+ request);
		
		try {
			Thread.sleep(220);
		} catch (InterruptedException e) {
			System.out.println("Exception: " + e);
		}
		
		for(int i=7; i<9; i++) {
			request.put(i, rt.request("A"));
		}
		
		System.out.println("Request4: "+ request);
	}
	

	@Test
	void testRequest1() {
		RateLimiter rt = new SlidingWindowRateLimiter(4);		
		Map<Integer, Boolean> reqA = req10(rt, "A", 1);
		Map<Integer, Boolean> reqB = req10(rt, "B", 1);
		
		System.out.println("RequestA: "+ reqA);
		System.out.println("RequestB: "+ reqB);
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			System.out.println("Exception: " + e);
		}
		
		reqA = req10(rt, "A", 100);
		reqB = req10(rt, "B", 100);
		System.out.println("RequestA: "+ reqA);
		System.out.println("RequestB: "+ reqB);
		
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			System.out.println("Exception: " + e);
//		}
	}

	private Map<Integer, Boolean> req10(RateLimiter rt, String id, int startSeq) {
		Map<Integer, Boolean> request = new HashMap<>(); 
		for(int i=startSeq+0; i<startSeq+16; i++) {
			try {
				Thread.sleep(100 + i*10);
			} catch (InterruptedException e) {
				System.out.println("Exception: " + e);
			}
			request.put(i, rt.request(id));
		}
		return request;
	}

}
