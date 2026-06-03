package com.suz.concurrency.trials;

import java.util.LinkedHashMap;

public class LinkedHashMapTests {

	public LinkedHashMapTests() {
		
		
	}
	
	public static void main(String[] args) {
		
		final int capacity = 4;
		LinkedHashMap<String, Long> cacheOfLongs = new LinkedHashMap<>(
				/*
				 * The third Arg 'accessOrder' must be set to true
				 * for MRU (or LRU)
				 */
				capacity, 0.75f, true) {
			@Override
			protected boolean removeEldestEntry(java.util.Map.Entry<String, Long> eldest) {
				return size() > capacity;
			}
		};
		
		
		cacheOfLongs.put("A", 10L);
		cacheOfLongs.put("B", 20L);
		cacheOfLongs.put("C", 30L);
		cacheOfLongs.put("D", 40L);
		cacheOfLongs.put("E", 50L);
		
		System.out.println(cacheOfLongs);
		System.out.println(cacheOfLongs.get("B"));
		System.out.println(cacheOfLongs);
		cacheOfLongs.put("F", 60L);
		System.out.println(cacheOfLongs);
	}

}
