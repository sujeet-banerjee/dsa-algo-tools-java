/**
 *
 */
package algo.dyn.lcs;

/**
 * @author sujeet
 *
 */
public class Utils {

    public static int max(int... intSeq) {
        if (intSeq == null) {
            throw new IllegalArgumentException("Max: Inputs cannot be null");
        }

        if (intSeq.length == 0) {
            throw new IllegalArgumentException(
                    "Max: At least one int expected");
        }

        int max = intSeq[0];
        for (int ii : intSeq) {
            if (ii > max) {
                max = ii;
            }
        }

        return max;
    }

}
