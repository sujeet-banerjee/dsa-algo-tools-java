package com.suz;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {
	
	
	public static void main(String[] args) {
		char [] inputsSeq = new char[] {
			'A', 'B', 'B', 'A', 'A', 'C', 'A', 'C', 'B'
		};
		
		// Find the frequency of each number
        Map<Character, Integer> numFrequencyMap = new HashMap<>();
        for (char ch : inputsSeq) {
            numFrequencyMap.put(ch, 
            		numFrequencyMap.getOrDefault(ch, 0) + 1);
        }

        Comparator<Entry<Character, Integer>> comparator = 
        		(e1, e2) -> e1.getValue() - e2.getValue();
        
        // Java like comparator to the above
		Comparator<Entry<Character, Integer>> comparator2 = 
				new Comparator<Map.Entry<Character,Integer>>() {
					@Override
					public int compare(Entry<Character, Integer> o1, 
							Entry<Character, Integer> o2) {
						// TODO Auto-generated method stub
						return o1.getValue() - o2.getValue();
					}
				};
        		
		PriorityQueue<Map.Entry<Character, Integer>> topKElements = 
        		new PriorityQueue<>(comparator);
		
		// Fillin
		int k = 2;
		for (Map.Entry<Character, Integer> entry : numFrequencyMap.entrySet()) {
            topKElements.add(entry);
            
            // This ensures, at any given time, 
            // only k elements will be present inside the PQ.
//            if (topKElements.size() > k) {
//                topKElements.poll();
//            }
        }
		
		// Not necessarily sorted.
        System.out.println("PQ: "+topKElements);
        for(Map.Entry<Character, Integer> elm: topKElements) {
        	System.out.println("PQ elm: "+elm);
        }
        
        for(int i=0; i<topKElements.size(); ) {
        	Entry<Character, Integer> elm = topKElements.poll();
        	System.out.println("Removed PQ elm: "+elm);
        }
        
        
        // FOR SORTED SET!!!
        SortedSet<Map.Entry<Character, Integer>> ss = new TreeSet<>(comparator);
        for (Map.Entry<Character, Integer> entry : numFrequencyMap.entrySet()) {
            ss.add(entry);
        }
        
        System.out.println("\n\nSS: "+ss);
        
	}

}	
