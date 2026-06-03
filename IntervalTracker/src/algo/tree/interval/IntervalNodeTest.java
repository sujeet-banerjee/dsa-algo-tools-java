package algo.tree.interval;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class IntervalNodeTest {

    @Test
    void testIsLeaf() {
        IntervalNode root = new IntervalNode(new Interval(2, 3));
        IntervalNode left = new IntervalNode(new Interval(1, 2));
        IntervalNode right = new IntervalNode(new Interval(3, 4));

        root.setLeft(left);
        root.setRight(right);

        Assert.assertTrue("Is a Leaf: left", left.isLeaf());
        Assert.assertTrue("Is a Leaf: right", right.isLeaf());
        Assert.assertFalse("Is NOT a Leaf: root", root.isLeaf());

        try {
            left.setLeft(left);
            Assert.fail("Cyclic references should be caught for Left");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            right.setRight(right);
            Assert.fail("Cyclic references should be caught for Left");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void testMaxLevel1() {
        IntervalNode root = new IntervalNode(new Interval(2, 3));
        Assert.assertEquals(3, root.getMax());

        IntervalNode left = new IntervalNode(new Interval(1, 2));
        root.setLeft(left);
        Assert.assertEquals(3, root.getMax());
        Assert.assertEquals(2, root.getLeft().getMax());

        IntervalNode right = new IntervalNode(new Interval(3, 4));
        root.setRight(right);
        Assert.assertEquals(4, root.getRight().getMax());
        Assert.assertEquals(4, root.getMax());
    }

    @Test
    void testMaxLevel2() {
        IntervalNode root = new IntervalNode(new Interval(2, 3));
        Assert.assertEquals(3, root.getMax());

        IntervalNode left = new IntervalNode(new Interval(1, 2));
        root.setLeft(left);
        Assert.assertEquals(3, root.getMax());
        Assert.assertEquals(2, root.getLeft().getMax());

        IntervalNode right = new IntervalNode(new Interval(3, 4));
        root.setRight(right);
        Assert.assertEquals(4, root.getRight().getMax());
        Assert.assertEquals(4, root.getMax());

        right.setRight(new IntervalNode(new Interval(5, 7)));
        Assert.assertEquals(7, right.getRight().getMax());
        Assert.assertEquals(7, right.getMax());

        // No change to the left side
        Assert.assertEquals(2, root.getLeft().getMax());

        // Right subtree update should have updated the root.max
        Assert.assertEquals(7, root.getMax());
    }

}
