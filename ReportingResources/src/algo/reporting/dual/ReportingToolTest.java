/**
 * 
 */
package algo.reporting.dual;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import algo.reporting.dual.Entry;

/**
 * WORKED!!!
 */
class ReportingToolTest {

	/**
	 * Test method for {@link algo.reporting.dual.ReportingTool#insert(algo.reporting.dual.Entry)}.
	 */
	@Test
	void testInsert() {
		ReportingTool rp = new ReportingTool();
		
		rp.insert(new Entry("f3", 100, "c2"));
		rp.insert(new Entry("f4", 110, null));
		rp.insert(new Entry("f5", 120, ""));
		rp.insert(new Entry("f1", 100, "c1"));
		rp.insert(new Entry("f2", 100, "c1"));
		
		rp.insert(new Entry("f33", 200, "c2"));

		
		rp.insert(new Entry("f44", 200, "c3"));
		rp.insert(new Entry("f45", 300, "c4"));
		
		rp.insert(new Entry("f46", 200, "c5"));
		rp.insert(new Entry("f47", 300, "c5"));
	}

}
