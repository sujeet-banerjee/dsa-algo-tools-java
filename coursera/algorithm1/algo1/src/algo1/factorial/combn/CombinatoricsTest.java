package algo1.factorial.combn;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class CombinatoricsTest {

    @Test
    void testNCr() {
        Assert.assertEquals(1, Combinatorics.nCr(1, 0));
        Assert.assertEquals(1, Combinatorics.nCr(1, 1));
        Assert.assertEquals(1, Combinatorics.nCr(3, 0));
        Assert.assertEquals(3, Combinatorics.nCr(3, 1));
        Assert.assertEquals(3, Combinatorics.nCr(3, 2));
        Assert.assertEquals(1, Combinatorics.nCr(3, 3));
    }

    @Test
    void testNCrLarge() {
        // 100C11 => 141629804643600

        System.out.println("MAX_LONG: " + Long.MAX_VALUE);

        long expectedLongValue = Long.valueOf("141629804643600");
        System.out.println("EXPECTED: " + expectedLongValue);
        Assert.assertEquals(expectedLongValue, Combinatorics.nCr(100, 11));
    }

}
