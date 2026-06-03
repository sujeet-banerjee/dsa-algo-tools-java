/**
 *
 */
package algo1.factorial.combn;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sujeet
 *
 */
public class Combinatorics {

    private static final Map<Pair, Long> MEM = new HashMap<>();

    public static long nCr(int n, int r) {
        if(n<1) {
            throw new IllegalArgumentException("'n' must be a +ve integer");
        }

        if(r<0 || r >n) {
            throw new IllegalArgumentException(
                    "'r' must be a non-negative integer, and must not exceed 'n'");
        }

        // Optimization
        r = r > n / 2 ? n - r : r;

        if (r == 0 || r == n) {
            return 1;
        }

        final Pair pair = new Pair(n, r);
        if (MEM.containsKey(pair)) {
            return MEM.get(pair);
        }

        long value = nCr(n - 1, r) + nCr(n - 1, r - 1);
        MEM.put(pair, value);
        System.out.println(
                String.format("- nCr %s ==> %s", pair, value));
        return value;
    }

}
