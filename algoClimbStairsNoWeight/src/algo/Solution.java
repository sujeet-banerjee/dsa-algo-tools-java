package algo;

/**
 * PROBLEM:
 * You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step


Constraints:

1 <= n <= 45

-------------------------
/
 *
 *
 * I am resubmitting it!
 *
 * This doesn't really need a Dynamic-Programming! There is a close-form
 * solution that exists - that is, a formula driven approach - which goes back
 * to "Permutation and Combinations" from the school-level mathematics.
 *
 * If we have k-identical balls mixed with m-identical balls, and then try to
 * arrange those in a straight line - the number of permutations will be
 * (m+k)-choose-(m), that is (m+k)! / (m! * k!)
 *
 * Now, it may be computationally intensive to calculate a factorial. But we
 * know that the result of a "combination" formula is always going to be an
 * integer - so we use another recursive formula:
 *
 * combn(p, k) = combn(p-1, k) + combn(p-1, k-1)
 *
 * Which looks more like a Fibbonachi formula.
 *
 * Solution: At each iteration, we model number of single-steps as k and the
 * number of double-steps as m, and find the number of arrangements possible
 * (note: each iteration will be run with a different k and m). The number of
 * iterations will be the maximum number of double-steps possible, which is
 * given by the formula:
 *
 * n = 2 * m + k, where m starts from 0 (first iteration) until "n div 2" (i.e
 * the last iteration).
 *
 */

import java.util.Map;
import java.util.HashMap;

class Solution {
    public static Map<Integer, Map<Integer, Long>> COMBN = new HashMap<>();

    public static long combn(int m, int k) {
        if (k < 0 || m < 0) {
            throw new IllegalArgumentException("K and/or M cannnot be -ve");
        }

        if (k > m) {
            throw new IllegalArgumentException("K must be <= M");
        }

        if (k == 0 || m == k) {
            return 1;
        }

        // Optimization
        k = k > m / 2 ? m - k : k;

        if (COMBN.containsKey(m)) {
            Map<Integer, Long> map = COMBN.get(m);

            if (map.containsKey(k)) {
                return map.get(k);
            }
        } else {
            COMBN.put(m, new HashMap<>());
        }

        long ret = Solution.combn(m - 1, k) + Solution.combn(m - 1, k - 1);
        COMBN.get(m).put(k, ret);
        return ret;
    }

    public long climbStairs(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("N must be +ve");
        }

        if (n <= 3) {
            return n;
        }

        long total = 0;
        for (int dStep = 0; dStep <= n / 2; dStep++) {
            // Note: n = 2 * m + k
            // Or (n - m) == (m + k)

            // Permutations for dSteps + sSteps combined:
            long res = Solution.combn( (n - dStep), dStep);
            if(res < 0) {
                throw new IllegalStateException("Arithmetic Overflow!! Dstep="+ (n-dStep));
            }

            System.out.println(String.format("%d -choose- %d = %d",
                    (n - dStep), dStep, res));
            total += res;

            if (total < 0) {
                throw new IllegalStateException(
                        "Arithmetic Overflow!! Dstep=" + (n - dStep));
            }
        }


        return total;
    };

    public static void main(String[] args) {
        System.out.println("N=80 ; Ans=" + new Solution().climbStairs(80));
    }
}