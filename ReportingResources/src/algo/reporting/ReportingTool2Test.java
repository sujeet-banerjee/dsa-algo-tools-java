package algo.reporting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ReportingTool2Test {

	@Test
	void testInsert() {
		ReportingTool2 rp = new ReportingTool2();
		
		rp.insert(new Entry("f3", 100, "c2"));
		Assert.assertTrue("Should Contain", rp.getSortedStore().containsKey("c2"));
		Assert.assertEquals(Integer.valueOf(100), rp.getSortedStore().get("c2"));
		
		rp.insert(new Entry("f4", 110, null));
		Assert.assertTrue("Should Contain", rp.getSortedStore().containsKey("f4"));
		Assert.assertEquals(Integer.valueOf(110), rp.getSortedStore().get("f4"));
		
		rp.insert(new Entry("f5", 120, ""));
		Assert.assertTrue("Should Contain", rp.getSortedStore().containsKey("f5"));
		Assert.assertEquals(Integer.valueOf(120), rp.getSortedStore().get("f5"));
		
		rp.insert(new Entry("f1", 100, "c1"));
		Assert.assertTrue("Should Contain", rp.getSortedStore().containsKey("c1"));
		Assert.assertEquals(Integer.valueOf(100), rp.getSortedStore().get("c1"));
		
		rp.insert(new Entry("f2", 100, "c1"));
		Assert.assertTrue("Should Contain", rp.getSortedStore().containsKey("c1"));
		Assert.assertEquals(Integer.valueOf(200), rp.getSortedStore().get("c1"));

		System.out.println("RP store Assorted: "+ rp.getStore());
		System.out.println("RP store Sorted: "+ rp.getSortedStore());
		
		rp.insert(new Entry("f33", 200, "c2"));
		Assert.assertTrue("Should Contain", rp.getSortedStore().containsKey("c2"));
		Assert.assertEquals(Integer.valueOf(300), rp.getSortedStore().get("c2"));
		
		/*
		 * Does not work, as the TreeMap is sorting based on values, so
		 * it could not distinguish {c2=100}, {c2=300}
		 */
		System.out.println("RP store Assorted: "+ rp.getStore());
		System.out.println("RP store Sorted: "+ rp.getSortedStore());
	}

}
