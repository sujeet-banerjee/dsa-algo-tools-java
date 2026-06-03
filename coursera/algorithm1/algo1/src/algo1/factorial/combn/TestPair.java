/**
 *
 */
package algo1.factorial.combn;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


/**
 * @author sujeet
 *
 */
class TestPair {

    /**
     * Test method for {@link algo1.factorial.combn.Pair#hashCode()}.
     */
    @Test
    void testHashCode() {
        Pair<Integer, Integer> p1 = new Pair<>(2, 3);
        Pair<Integer, Integer> p1Copy = new Pair<>(2, 3);
        Pair<Integer, Integer> p1Rev = new Pair<>(3, 2);

        System.out.println("Pair p1: " + p1);
        System.out.println("Pair p1 Copy: " + p1Copy);
        System.out.println("Pair p1 Reverse: " + p1Rev);

        System.out.println("HC p1: " + p1.hashCode());
        System.out.println("HC p1 Copy: " + p1Copy.hashCode());
        System.out.println("HC p1 Reverse: " + p1Rev.hashCode());

        Assert.assertTrue("Hash codes must be same",
                p1.hashCode() == p1Copy.hashCode());
        Assert.assertTrue("Hash codes must be different",
                p1.hashCode() != p1Rev.hashCode());
    }

    /**
     * Test method for {@link algo1.factorial.combn.Pair#getFirst()}.
     */
    @Test
    void testGetFirst() {
        Assert.assertEquals(4, new Pair(4, 3).getFirst());
        Assert.assertEquals(4, new Pair(Integer.valueOf(4), 3).getFirst());
        // Assert.assertEquals(4, new Pair(null, null).getFirst());
    }

    // TODO test creation and non-null components

    /**
     * Test method for {@link algo1.factorial.combn.Pair#getSecond()}.
     */
    @Test
    void testGetSecond() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link algo1.factorial.combn.Pair#equals(java.lang.Object)}.
     */
    @Test
    void testEqualsSame() {
        Pair<Integer, Integer> p1 = new Pair<>(2, 3);
        Pair<Integer, Integer> p1Copy = new Pair<>(2, 3);

        Assert.assertFalse(
                "The objects must not be same", p1 == p1Copy);
        Assert.assertTrue(
                "The objects must satisfy equals", p1.equals(p1Copy));
        Assert.assertTrue(
                "The objects must satisfy equals", p1Copy.equals(p1));

    }

    @Test
    void testEqualsDifferent() {
        Pair<Integer, Integer> p1 = new Pair<>(2, 3);
        Pair<Integer, Integer> p1Rev = new Pair<>(3, 2);

        Assert.assertFalse(
                "The objects must not be same", p1 == p1Rev);
        Assert.assertTrue(
                "The objects must satisfy equals", !p1.equals(p1Rev));
        Assert.assertTrue(
                "The objects must satisfy equals", !p1Rev.equals(p1));
    }

}
