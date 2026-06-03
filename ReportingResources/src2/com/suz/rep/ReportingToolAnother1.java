package com.suz.rep;

import java.util.Map;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

public class ReportingToolAnother1 {
	
	private final Map<String, Map.Entry<Integer, String>> aggregate;
	private final SortedSet<Map.Entry<Integer, String>> sortedAggregate;

	public ReportingToolAnother1() {
		this.aggregate = new HashMap<>();
		this.sortedAggregate = new TreeSet<>(
				(e1, e2) -> {
					
					/**
					 * ERR1
					 * Missed this!
					 * 
					 * We cannot miss this, else 
					 *  (300, 'f1') will be overwritten by (300, 'f2'),
					 *  given the key comparison (numbers) that follows...
					 */
					if(e2.getKey().intValue()==e1.getKey().intValue()) {
						return e2.getValue().compareTo(e1.getValue());
					}
					
					return Integer.compare(e2.getKey().intValue(), 
							e1.getKey().intValue());
					});
	}
	
	public void addFile(Map.Entry<String, Integer> file, String tag) {
		if(!this.aggregate.containsKey(tag)) {
			Map.Entry<Integer, String> newEntry = Map.entry(
					file.getValue(), 
						// ERR2 - missed to check the null/"" in the first attempt
						tag == null || tag.isBlank() ? file.getKey() : tag);
			this.aggregate.put(tag, newEntry);
			this.sortedAggregate.add(newEntry);
		} else {
			Map.Entry<Integer, String> oldEntry = this.aggregate.get(tag);
			
			Map.Entry<Integer, String> newEntry = Map.entry(
					oldEntry.getKey() + file.getValue(), 
					tag);
			
			if(this.sortedAggregate.remove(oldEntry))
			{
				// successful
				this.sortedAggregate.add(newEntry);
			} else {
				// Error
			}
			
		}
	}
	
	public void print() {
		System.out.println(this.sortedAggregate);
	}
	
	@Test
	void testAdd() {
		ReportingToolAnother1 rp = new ReportingToolAnother1();
		
		rp.addFile(new SimpleImmutableEntry("f3", 100), "c2");
		rp.addFile(new SimpleImmutableEntry("f4", 110), null);
		rp.addFile(new SimpleImmutableEntry("f5", 120), "");
		System.out.println("Report: "+ rp.getReport());
		
		rp.addFile(new SimpleImmutableEntry("f1", 100), "c1");
		rp.addFile(new SimpleImmutableEntry("f2", 100), "c1");
		System.out.println("Report: "+ rp.getReport());
		
		rp.addFile(new SimpleImmutableEntry("f33", 200), "c2");
		System.out.println("Report: "+ rp.getReport());
		
		rp.addFile(new SimpleImmutableEntry("f44", 200), "c3");
		rp.addFile(new SimpleImmutableEntry("f45", 300), "c4");
		System.out.println("Report: "+ rp.getReport());
		
		rp.addFile(new SimpleImmutableEntry("f46", 200), "c5");
		rp.addFile(new SimpleImmutableEntry("f47", 300), "c5");
		System.out.println("Report: "+ rp.getReport());
	}

	private SortedSet<Entry<Integer, String>>  getReport() {
		return this.sortedAggregate;
	}

}
