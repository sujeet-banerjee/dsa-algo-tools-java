package algo.dyn.lcs;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class LongestCommonSubsequenceTest {

    @Test
    void testFindLCS() {
        // Data from Cormen, Page 350
        String s1 = "ACCGGTCGAGTGCGCGGAAGCCGGCCGAA";
        String s2 = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";
        String lcsExpected = "GTCGTCGGAAGCCGGCCGAA";

        String lcsActual = new LongestCommonSubsequence().findLCS(s1, s2);
        Assert.assertEquals(lcsExpected, lcsActual);
    }

    @Test
    void testFindLCSBasic() {
        LongestCommonSubsequence lcsHelper = new LongestCommonSubsequence();
        Assert.assertEquals("A", lcsHelper.findLCS("A", "A"));
        Assert.assertEquals("", lcsHelper.findLCS("A", "B"));

        Assert.assertEquals("A", lcsHelper.findLCS("AB", "A"));
        Assert.assertEquals("A", lcsHelper.findLCS("A", "BA"));

        Assert.assertEquals("A", lcsHelper.findLCS("AX", "YA"));
        Assert.assertEquals("", lcsHelper.findLCS("AX", "BY"));

        Assert.assertEquals("BDFG", lcsHelper.findLCS("ABCDEFGH", "B1DCFEIG"));
    }

    @Test
    void testMax() {
        Assert.assertEquals(33, Utils.max(3, 33));
        Assert.assertEquals(33, Utils.max(33, 3));
        Assert.assertEquals(-1, Utils.max(-1));
        Assert.assertEquals(100, Utils.max(100, 100));
    }

}
