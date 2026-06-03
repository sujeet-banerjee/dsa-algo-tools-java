package algo.reporting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

class ReportingToolTest {

	
	@Test
	void testInsert() {
		ReportingTool rp = new ReportingTool();
		rp.insert(new Entry("f3", 100, "c2"));
		rp.insert(new Entry("f4", 110, null));
		rp.insert(new Entry("f5", 120, ""));
		rp.insert(new Entry("f1", 100, "c1"));
		rp.insert(new Entry("f2", 100, "c1"));

		System.out.println("RP store: "+ rp.getStore());
	 	System.out.println("Report Assorted: "+ rp.store.entrySet() );
	 	
	 	ArrayList<Map.Entry<String, Integer>> toList = new ArrayList<>(
					rp.store.entrySet());
	 	
		Collections.sort(toList, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(
					java.util.Map.Entry<String, Integer> o1, 
					java.util.Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
	 	System.out.println("Report Sorted: "+ toList);
	}

}
