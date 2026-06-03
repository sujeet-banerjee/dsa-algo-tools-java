/**
 *
 */
package c.s.t.bits;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author sujeet
 *
 */
class BinaryGetSetProblemTest {

    /**
     * Test method for {@link c.s.t.bits.BinaryGetSetProblem#answerQuery(java.util.List, int)}.
     */
    @Test
    void testAnswerQuery() {

        List<Query> queryList = new ArrayList<>();
        queryList.add(new Query(2, 1));
        // Set
        queryList.add(new Query(1, 3));
        queryList.add(new Query(2, 1));

        System.out.println("QUERY-LIST: " + queryList);

        int[] actual = new BinaryGetSetProblem().answerQuery(queryList, 10);
        int[] expected = new int[] { -1, 3 };

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    void binOps() {
        BinaryGetSetProblem binaryGetSetProblem = new BinaryGetSetProblem();
        int a = binaryGetSetProblem.setBitToIndex(0, 10);
        BinaryGetSetProblem.printBin(a);
        a = ~a;
        BinaryGetSetProblem.printBin(a);
    }
}
