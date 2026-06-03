package com.suz.attempt2;

import java.util.Map;
import java.util.Iterator;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.HashMap;
import static java.util.Map.Entry;
import static java.util.AbstractMap.SimpleImmutableEntry;

public class PriorityQueueOnDual {
	// Dual ==> Map.SimpleImmutableEntry
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		init(map);
		Queue<Entry<String, Integer>> pq = prioritize(map, false);
		
		// Note: the print order may not be sorted!
		System.out.println("Pri Q: "+ pq);
		
		dequque(pq);
	}
	
	public static void init(Map<String, Integer> map) {
		map.put("One", 22);
		map.put("Two", 20);
		map.put("Three", 18);
		map.put("Four", 15);
	}
	
	public static Queue<Entry<String, Integer>> prioritize(
			Map<String, Integer> map, boolean reverse) {
		
		Queue<Entry<String, Integer>> pq = new PriorityQueue<>(
				(e1, e2) -> {
						int comp = e1.getValue() - e2.getValue();
						comp = reverse? -comp: comp;
						return comp;
					}
				);
		
		for(Entry<String, Integer> e: map.entrySet()) {
			System.out.println("Insert entry: "+ e);
			pq.offer(e);
		}
		
		// 'Contains' test
		SimpleImmutableEntry toBeRemoved = new SimpleImmutableEntry("New", 1);
		Entry<String, Integer> newIt = toBeRemoved;
		System.out.println("Present "+ 
				newIt+ ": "+ pq.contains(newIt));
		
		newIt = new SimpleImmutableEntry("One", 22);
		System.out.println("Present "+ 
				newIt+ ": "+ pq.contains(newIt));
		
		// targetted 'deletion' test
		newIt = toBeRemoved;
		System.out.println("Insert new entry: "+ newIt);
		pq.offer(newIt);
		
		System.out.println("Present "+ 
				newIt+ ": "+ pq.contains(newIt));
		System.out.println("Pri Q: "+ pq);
		if(pq.remove(toBeRemoved)) {
			System.out.println("Removed! entry="+ toBeRemoved);
		}
		
		return pq;
	}
	
	public static void dequque(Queue<Entry<String, Integer>> pq) {
		// This won't be sorted, again!
//		for(Entry<String, Integer> e : pq) {
//			System.out.println("Entry: "+ e);
//		}
		
		Iterator<Entry<String, Integer>> itr = pq.iterator();
		
		while(itr.hasNext()) {
			Entry<String, Integer> e = itr.next();
			System.out.println("De-queued Entry: "+ e);
			itr.remove();
		}
		
		// Alt: you can count 1..size and do pq.poll();
		// same result!
	}
	
}


