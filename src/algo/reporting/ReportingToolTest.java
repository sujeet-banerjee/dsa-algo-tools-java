/**
 *
 */
package algo.reporting;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 *
 */
class ReportingToolTest {

    /**
     * Test method for
     * {@link algo.reporting.ReportingTool#insert(algo.reporting.Entry)}.
     */
    @Test
    void testInsert() {
        ReportingTool rp = new ReportingTool();

        rp.insert(new Entry("f3", 100, "c2"));
        rp.insert(new Entry("f4", 110, null));
        rp.insert(new Entry("f5", 120, ""));
        rp.insert(new Entry("f1", 100, "c1"));
        rp.insert(new Entry("f2", 100, "c1"));
        System.out.println("------");
        rp.printReport();

        /*
         * TODO write a utility to read from a String for test validations
         */
        Entry[] expectedEntries = new Entry[] {
                new Entry("x", 200, "c1"),
                new Entry("y", 120, "f5"),
                new Entry("z", 110, "f4"),
                new Entry("w", 100, "c2"),
                };
        Entry[] actualEntries = rp.getReport()
                        .<Entry>toArray(new Entry[] {});
        Assert.assertArrayEquals(
                expectedEntries,
                        actualEntries);

        rp.insert(new Entry("f33", 200, "c2"));
        rp.insert(new Entry("f44", 200, "c3"));
        rp.insert(new Entry("f45", 300, "c4"));
        System.out.println("------");
        rp.printReport();
        expectedEntries = new Entry[] {
                new Entry("x", 300, "c4"),
                new Entry("y", 300, "c2"),
                new Entry("z", 200, "c3"),
                new Entry("u", 200, "c1"),
                new Entry("v", 120, "f5"),
                new Entry("w", 110, "f4"),
                };
        Assert.assertArrayEquals(
                expectedEntries,
                rp.getReport().<Entry>toArray(new Entry[] {}));


        rp.insert(new Entry("f46", 200, "c5"));
        rp.insert(new Entry("f47", 300, "c5"));
        System.out.println("------");
        rp.printReport();
        expectedEntries = new Entry[] {
                new Entry("a", 500, "c5"),
                new Entry("b", 300, "c4"),
                new Entry("c", 300, "c2"),
                new Entry("d", 200, "c3"),
                new Entry("e", 200, "c1"),
                new Entry("f", 120, "f5"),
                new Entry("g", 110, "f4"),
                };
        Assert.assertArrayEquals(
                expectedEntries,
                rp.getReport().<Entry>toArray(new Entry[] {}));
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(
                new Entry("f", 120, "c1"),
                new Entry("g", 300, "c1"));

        Assert.assertNotEquals(
                new Entry("f", 120, "c1"),
                new Entry("f", 120, "c2"));
    }

    @Test
    public void testCompareTo() {
        // Reverse order
        Assert.assertTrue("Incorrect comparison order",
                new Entry("g", 120, "c1").compareTo(
                        new Entry("f", 300, "c1")) > 0);
        Assert.assertTrue("Incorrect comparison order",
                new Entry("f", 300, "c1").compareTo(
                        new Entry("g", 120, "c1")) < 0);

        Assert.assertEquals(
                "Incorrect comparison order", 0,
                new Entry("f", 120, "c1").compareTo(
                        new Entry("g", 120, "c1")));

        Assert.assertTrue("Incorrect comparison order",
                new Entry("f", 120, "c1").compareTo(
                        new Entry("g", 120, "c2")) > 0);
    }
}
