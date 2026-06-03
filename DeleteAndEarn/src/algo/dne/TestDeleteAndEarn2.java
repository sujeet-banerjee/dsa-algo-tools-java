package algo.dne;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TestDeleteAndEarn2 {

    @Test
    void testDeleteAndEarnSmallSize() {
        int[] nums = new int[] { 2, 2, 3, 3, 3, 4 };
        int val = new DeleteAndEarn2().deleteAndEarn(nums);
        System.out.println("Earned: " + val);

        Assert.assertEquals(9, val);
    }

    @Test
    void testDeleteAndEarnBigSize() {
        int[] nums = new int[] { 12, 32, 93, 17, 100, 72, 40, 71, 37, 92, 58,
                34, 29, 78, 11, 84, 77, 90, 92, 35, 12, 5, 27, 92, 91, 23, 65,
                91, 85, 14, 42, 28, 80, 85, 38, 71, 62, 82, 66, 3, 33, 33, 55,
                60, 48, 78, 63, 11, 20, 51, 78, 42, 37, 21, 100, 13, 60, 57, 91,
                53, 49, 15, 45, 19, 51, 2, 96, 22, 32, 2, 46, 62, 58, 11, 29, 6,
                74, 38, 70, 97, 4, 22, 76, 19, 1, 90, 63, 55, 64, 44, 90, 51,
                36, 16, 65, 95, 64, 59, 53, 93 };
        int val = new DeleteAndEarn2().deleteAndEarn(nums);
        System.out.println("Earned: " + val);

        Assert.assertEquals(3451, val);
    }

    @Test
    void testMax() {
        int actual = DeleteAndEarn2.max(5, 3, -1, 2, 3, 5, 99, 0, 99);
        Assert.assertEquals("", 99, actual);
    }

    @Test
    void testCalculateCount() {
        int[] nums = new int[] { 2, 2, 3, 3, 3, 4 };
        Map<Integer, Integer> count =
                new DeleteAndEarn2().calculateCount(nums);

        Assert.assertEquals(2, (int) (count.get(2)));
        Assert.assertEquals(3, (int) (count.get(3)));
        Assert.assertEquals(1, (int) (count.get(4)));
    }

    @Test
    void testMakeCopy() {
        Set<Integer> src = new HashSet<Integer>(
                Arrays.asList(new Integer[] { 3, 4, 6, 7, 9, 10, 3 }));

        // System.out.println("Src: " + src + "; Hash=" + (src.hashCode()));
        Set<Integer> cp = new DeleteAndEarn2().makeCopy(src);

        Assert.assertEquals("HashCodes did not match", src.hashCode(),
                cp.hashCode());
        Assert.assertTrue("The src and copy should not be the same references",
                src != cp);
        // System.out.println("CP = " + cp + "; Hash=" + (cp.hashCode()));

        Set<Integer> emptyCp = new DeleteAndEarn2()
                .makeCopy(Collections.EMPTY_SET);
        Assert.assertEquals(0, emptyCp.size());
    }

}
