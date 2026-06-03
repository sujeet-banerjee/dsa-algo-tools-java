package com.suz.concurrency.caching.lru;

import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.Map.Entry;

import java.util.AbstractMap.SimpleEntry;

/**
 * Use of LinedHashMap with MRU config
 */
public class LRUCacheWithTTL {
	private int capacity;
	private int defaultTTLInMillis = 10000;
	
	LinkedHashMap<String, Map.Entry<Object, Long>> cache = 
			new LinkedHashMap<>(capacity, 0.75f, true) {
		
		@Override
		public boolean removeEldestEntry(
				Map.Entry<String, Map.Entry<Object, Long>> e) {
			return size() > capacity
					|| e.getValue().getValue() < System.currentTimeMillis();
		}
	};
	

	public LRUCacheWithTTL(int capacity, int defaultTTLInMillis) {
		this.defaultTTLInMillis = defaultTTLInMillis;
		this.capacity = capacity;
	}
	
	public LRUCacheWithTTL(int capacity) {
		this(capacity, 10000);
	}
	
	public void put(String key, Object val, long ttlInMillis) {
		this.cache.put(key, new SimpleEntry<>(
				val, System.currentTimeMillis() + ttlInMillis));
	}
	
	public void put(String key, Object val) {
		this.put(key, val, this.defaultTTLInMillis);
	}
	
	public Object get(String key) {
		Map.Entry<Object, Long> val = cache.get(key);
		if(val==null) {
			return null;
		}
		
		/*
		 * THIS IS STILL NEEDED, even though the removeEldestEntry
		 * method seems to take care of this logic!!
		 */
		if(val.getValue() < System.currentTimeMillis()) {
			cache.remove(key);
			return null;
		}
		return val.getKey();
	}
	
	public static void main(String[] args) throws Exception {
		LRUCacheWithTTL cacheOfLongs = new LRUCacheWithTTL(4, 1000);
		
		cacheOfLongs.put("A", 10L);
		cacheOfLongs.put("B", 20L);
		cacheOfLongs.put("C", 30L);
		cacheOfLongs.put("D", 40L);
		cacheOfLongs.put("E", 50L);
		
		System.out.println(cacheOfLongs.cache);
		System.out.println(cacheOfLongs.get("B"));
		System.out.println(cacheOfLongs.cache);
		cacheOfLongs.put("F", 60L, 10000);
		System.out.println(cacheOfLongs.cache);
		
		/*
		 * Should print:
		 {B=20, C=30, D=40, E=50}
		 20
		 {C=30, D=40, E=50, B=20}
		 {D=40, E=50, B=20, F=60}
		 */
		
		Thread.sleep(3000);
		System.out.println(">> D=" + cacheOfLongs.get("D"));
		System.out.println(cacheOfLongs.cache);
		cacheOfLongs.put("G", 70L, 10000);
		System.out.println(cacheOfLongs.cache);
		System.out.println(">> G=" + cacheOfLongs.get("G"));
		System.out.println(cacheOfLongs.cache);
		System.out.println(">> F=" + cacheOfLongs.get("F"));
		System.out.println(cacheOfLongs.cache);
		System.out.println(">> E=" + cacheOfLongs.get("E"));
		System.out.println(cacheOfLongs.cache);
		
		cacheOfLongs.put("B", 20L, 10000);
		System.out.println(cacheOfLongs.cache);
	}

}
