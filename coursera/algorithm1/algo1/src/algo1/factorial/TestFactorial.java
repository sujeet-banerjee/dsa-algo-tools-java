package algo1.factorial;

import java.util.Map;
import java.util.HashMap;

public class TestFactorial {
    Map<Integer, Map<Integer, Long>> cM = new HashMap<>();
    public long fact(long n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The n must be positive");
        }
        if (n <= 1) {
            return 1;
        }

        long factNbut1 = fact(n - 1);
        long ret = n * factNbut1;

        if (ret <= 0) {
            System.err.println(
                    String.format("<%s * %s = %s>", n, factNbut1, ret));
            throw new IllegalStateException("Overflow Error!");
        }
        return ret;
    }

    public long combn(int m, int k) {
        if (m < k) {
            throw new IllegalArgumentException("k > m");
        }

        if (m == k || k == 0) {
            return 1;
        }

        k = (m - k) < k ? m - k : k;

        if(cM.containsKey(m)) {
            Map<Integer, Long> choose = cM.get(m);
            if (choose.containsKey(k)) {
                return choose.get(k);
            }
        } else {
            cM.put(m, new HashMap<>());
        }

        long ret = combn(m - 1, k) + combn(m - 1, k - 1);
        if (ret < 0) {
            throw new IllegalStateException("Overflow Error!");
        }

        cM.get(m).put(k, ret);
        return ret;
    }


    /**
     * COMBN(50, 0) => 1
COMBN(50, 1) => 50
COMBN(50, 2) => 1225
COMBN(50, 3) => 19600
COMBN(50, 4) => 230300
COMBN(50, 5) => 2118760
COMBN(50, 6) => 15890700
COMBN(50, 7) => 99884400
COMBN(50, 8) => 536878650
COMBN(50, 9) => 2505433700
COMBN(50, 10) => 10272278170

COMBN(50, 11) => 37353738800

COMBN(50, 12) => 121399651100
     * @param args
     */
    public static void main(String[] args) {
        // System.out.println("FACT: " + fact(21));
        TestFactorial tf = new TestFactorial();
        for (int i = 0; i <= 50; i++) {
            System.out.println(
                    String.format("COMBN(%d, %d) => %d", 50, i, tf.combn(50, i)));
        }
    }

}
