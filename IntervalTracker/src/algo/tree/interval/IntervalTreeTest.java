package algo.tree.interval;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class IntervalTreeTest {

    @Test
    void testInsert() {
        IntervalTree tree = new IntervalTree();
        /**
         * [16, 21], [8,9], [25,30], [17,19], [5,8], [6,10]
         *
         * Followed by: [15,23], [26,26], [19,20], [0,3]
         */
        tree.insert(new Interval(16, 21));
        tree.insert(new Interval(8, 9));
        tree.insert(new Interval(25, 30));
        tree.insert(new Interval(17, 19));
        tree.insert(new Interval(5, 8));
        tree.insert(new Interval(6, 10));

        Assert.assertEquals(30, tree.getRoot().getMax());
        Assert.assertEquals(10, tree.getRoot().getLeft().getMax());
        Assert.assertEquals(30, tree.getRoot().getRight().getMax());
        Assert.assertEquals(19, tree.getRoot().getRight().getLeft().getMax());

        // Phase 2
        tree.insert(new Interval(15, 23));
        tree.insert(new Interval(26, 26));
        tree.insert(new Interval(19, 20));
        tree.insert(new Interval(0, 3));

        Assert.assertEquals(30, tree.getRoot().getMax());
        Assert.assertEquals(23, tree.getRoot().getLeft().getMax());
        Assert.assertEquals(30, tree.getRoot().getRight().getMax());
        Assert.assertEquals(20, tree.getRoot().getRight().getLeft().getMax());
    }

    @Test
    void testIsPresent() {
        fail("Not yet implemented");
    }

    @Test
    void testDelete() {
        fail("Not yet implemented");
    }

    @Test
    void testHasAnOverlap() {
        IntervalTree tree = new IntervalTree();
        /**
         * [16, 21], [8,9], [25,30], [17,19], [5,8], [6,10]
         *
         * Followed by: [15,23], [26,26], [19,20], [0,3]
         */
        tree.insert(new Interval(16, 21));
        tree.insert(new Interval(8, 9));
        tree.insert(new Interval(25, 30));
        tree.insert(new Interval(17, 19));
        tree.insert(new Interval(5, 8));
        tree.insert(new Interval(6, 10));

        // Phase 2
        tree.insert(new Interval(15, 23));
        tree.insert(new Interval(26, 26));
        tree.insert(new Interval(19, 20));
        tree.insert(new Interval(0, 3));

        Assert.assertEquals(30, tree.getRoot().getMax());
        Assert.assertEquals(23, tree.getRoot().getLeft().getMax());
        Assert.assertEquals(30, tree.getRoot().getRight().getMax());
        Assert.assertEquals(20, tree.getRoot().getRight().getLeft().getMax());

        Assert.assertTrue("Overlap expected",
                tree.hasAnOverlap(new Interval(22, 25)));
        Assert.assertFalse("Overlap NOT expected",
                tree.hasAnOverlap(new Interval(11, 14)));

        tree.insert(new Interval(40, 42));
        Assert.assertEquals(42, tree.getRoot().getMax());
        Assert.assertEquals(23, tree.getRoot().getLeft().getMax());
        Assert.assertEquals(42, tree.getRoot().getRight().getMax());
        Assert.assertEquals(20, tree.getRoot().getRight().getLeft().getMax());

        // Checking Idempotency
        Assert.assertTrue("Overlap expected",
                tree.hasAnOverlap(new Interval(22, 25)));
        Assert.assertFalse("Overlap NOT expected",
                tree.hasAnOverlap(new Interval(11, 14)));

        Assert.assertTrue("Overlap expected",
                tree.hasAnOverlap(new Interval(38, 41)));
        Assert.assertFalse("Overlap NOT expected",
                tree.hasAnOverlap(new Interval(38, 40)));
        Assert.assertFalse("Overlap NOT expected",
                tree.hasAnOverlap(new Interval(31, 39)));
    }

}
