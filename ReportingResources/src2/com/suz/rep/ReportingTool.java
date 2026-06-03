package com.suz.rep;

import java.util.Map;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import static java.util.Map.Entry;

import java.util.AbstractMap;

public class ReportingTool {
	
	/**
	 * Tag-str --> Tag-instance
	 * (assorted)
	 */
	Map<String, Entry<String, Integer>> tagSizes = new HashMap<>();
	/**
	 * Ordered based on the value
	 */
	SortedSet<Entry<String, Integer>> sortedSet = 
			new TreeSet<>((t1, t2) -> {
				// BLUNDER1 - Grave here: you the values are not int,
				// they are Integer objects: so Either use equals, or == with .intValue(
 				//if(t1.getValue() == t2.getValue()) {
					
				if(t1.getValue().intValue() == t2.getValue().intValue()) {
					return t2.getKey().compareTo(t1.getKey());
				}
				return t2.getValue() - t1.getValue();
			}
		);
	
	public void add(Entry<String, Integer> file, String tag) {
		if(tag==null || tag.isBlank()) {
			tag = file.getKey();
		}
		
		if(!tagSizes.containsKey(tag)) {
			Entry<String, Integer> tagE = 
					new AbstractMap.SimpleImmutableEntry<>(
							tag, file.getValue());
			
			/*
			 * 1. put in the aggregation (map)
			 * 2. add to the sorted-set 
			 */
			
//			System.out.println("~New Entry: "+ tagE);
			tagSizes.put(tag, tagE);
//			System.out.println("~Tag Map: "+ tagSizes);
//			System.out.println("~Sorted Set before: "+ sortedSet);
			
			// For debugging
			/*
			 * if(sortedSet.contains(tagE)) { for(Entry<String, Integer> oldT: sortedSet) {
			 * System.out.println(String.format( "----- oldT=%s newT=%s equals=%s CT=%s",
			 * oldT, tagE, oldT.equals(tagE), sortedSet.comparator().compare(oldT, tagE) ));
			 * }
			 * 
			 * throw new IllegalStateException("Already Present the new entry: "+ tagE); }
			 */
			
			sortedSet.add(tagE);
//			System.out.println("~Sorted Set after: "+ sortedSet);
//			System.out.println();
		} else {
			
			/* 1. Fetch the old entry from the map,
			 * and use that ref to remove old entry from the sorted-set as well.
			 * 2. put the updated entry in the aggregation (map)
			 * 2. and add the same to the sorted-set as well.
			 */
			
//			System.out.println("~Tag Map before: "+ tagSizes);
			
			// Find that in the SortedSet
			Map.Entry<String, Integer> tagE = tagSizes.remove(tag);
//			System.out.println("~OLD Entry: "+ tagE);
//			System.out.println("~Sorted Set before: "+ sortedSet);
			sortedSet.remove(tagE);
			
			// Make new Entry
			Map.Entry<String, Integer> newTagE = 
					new AbstractMap.SimpleImmutableEntry<>(
							tagE.getKey(), tagE.getValue() + file.getValue());
			tagSizes.put(tag, newTagE);
			sortedSet.add(newTagE);
//			System.out.println("~Tag Map after: "+ tagSizes);
//			System.out.println("~Sorted Set after: "+ sortedSet);
//			System.out.println();
		}
	}
	
	public SortedSet<Entry<String, Integer>> getReport() {
		return this.sortedSet;
	}
}
