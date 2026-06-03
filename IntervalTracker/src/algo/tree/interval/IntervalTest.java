/**
 *
 */
package algo.tree.interval;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author sujeet
 *
 */
class IntervalTest {

    /**
     * Test method for {@link algo.tree.interval.Interval#Interval(int, int)}.
     */
    @Test
    void testInterval() {
        Interval i1 = new Interval(2, 200);
        Assert.assertEquals(2, i1.getStart());
        Assert.assertEquals(200, i1.getEnd());

        try {
            Interval i2 = new Interval(-2, 200);
            Assert.fail("Illegal Interval error not thrown");
        } catch (Exception e) {
            // PASS
            System.out.println(e);
        }

        try {
            Interval i2 = new Interval(300, 200);
            Assert.fail("Illegal Interval error not thrown");
        } catch (Exception e) {
            // PASS
            System.out.println(e);
        }

        Interval i2 = new Interval(300, 300);
    }

    /**
     * Test method for {@link algo.tree.interval.Interval#isOverlapping(algo.tree.interval.Interval)}.
     */
    @Test
    void testIsOverlapping() {
        Interval i1 = new Interval(10, 25);
        Interval i2 = new Interval(23, 32);
        Interval i3 = new Interval(25, 32);

        Assert.assertTrue(i1.isOverlapping(i2));
        Assert.assertTrue(i2.isOverlapping(i1));

        Assert.assertFalse("Is non-overlapping", i3.isOverlapping(i1));
        Assert.assertFalse("Is non-overlapping", i1.isOverlapping(i3));
    }

    /**
     * Test method for {@link algo.tree.interval.Interval#isAtRight(algo.tree.interval.Interval)}.
     */
    @Test
    void testIsAtRight() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link algo.tree.interval.Interval#isAtLeft(algo.tree.interval.Interval)}.
     */
    @Test
    void testIsAtLeft() {
        fail("Not yet implemented");
    }
}
