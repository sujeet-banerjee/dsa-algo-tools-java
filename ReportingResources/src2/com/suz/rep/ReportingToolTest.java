package com.suz.rep;

import static org.junit.jupiter.api.Assertions.*;
import static java.util.AbstractMap.SimpleImmutableEntry;

import org.junit.jupiter.api.Test;


class ReportingToolTest {

	@Test
	void testAdd() {
		ReportingTool rp = new ReportingTool();
		
		rp.add(new SimpleImmutableEntry("f3", 100), "c2");
		rp.add(new SimpleImmutableEntry("f4", 110), null);
		rp.add(new SimpleImmutableEntry("f5", 120), "");
		System.out.println("Report: "+ rp.getReport());
		
		rp.add(new SimpleImmutableEntry("f1", 100), "c1");
		rp.add(new SimpleImmutableEntry("f2", 100), "c1");
		System.out.println("Report: "+ rp.getReport());
		
		rp.add(new SimpleImmutableEntry("f33", 200), "c2");
		System.out.println("Report: "+ rp.getReport());
		
		rp.add(new SimpleImmutableEntry("f44", 200), "c3");
		rp.add(new SimpleImmutableEntry("f45", 300), "c4");
		System.out.println("Report: "+ rp.getReport());
		
		rp.add(new SimpleImmutableEntry("f46", 200), "c5");
		rp.add(new SimpleImmutableEntry("f47", 300), "c5");
		System.out.println("Report: "+ rp.getReport());
	}

	@Test
	void testGetReport() {
		//fail("Not yet implemented");
	}

}
