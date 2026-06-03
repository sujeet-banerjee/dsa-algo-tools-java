/**
 *
 */
package algo.dyn.lcs;

/**
 * @author sujeet
 *
 */
public class LongestCommonSubsequence {

    public static final Pair<String, Integer> BLANK_PAIR = new Pair<>("", 0);

    /**
     * Returns the LCS of the specified strings.
     *
     * Assumes no space characters in the input strings.
     *
     * @param src
     * @param dest
     * @return
     */
    String findLCS(String src, String dest) {
        if (src == null || dest == null) {
            throw new IllegalArgumentException(
                    "The src or dest cannot be null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(src);
        sb.append(" --> ");
        sb.append(dest);
        sb.append("; EDIT: ");

        String[][] ss = new String[3][3];
        Pair<String, Integer>[][] mem = new Pair[src
                .length()][dest.length()];

        String res = this.computeLCS(src, 0, dest, 0, sb, mem).first;
        System.out.println(sb.toString());

        return res;
    }

    /**
     *
     * @param s1
     * @param i1
     * @param s2
     * @param i2
     * @param sb
     * @param mem
     * @return
     */
    Pair<String, Integer> computeLCS(
            String s1,
            int i1,
            String s2,
            int i2,
            StringBuilder sb,
            Pair<String, Integer>[][] mem) {

        if (i1 < 0 || i2 < 0) {
            throw new IllegalArgumentException("Indices cannot be zero");
        }

        if (i1 == s1.length() || i2 == s2.length()) {

            if (i1 < s1.length()) {
                sb.append(" Del( " + s1.substring(i1) + ")");
            }

            if (i2 < s2.length()) {
                sb.append(" Ins(" + s2.substring(i2) + ")");
            }

            return BLANK_PAIR;
        }

        if (mem[i1][i2] != null) {
            return mem[i1][i2];
        }

        if (s1.charAt(i1) == s2.charAt(i2)) {
            char common = s1.charAt(i1);
            Pair<String, Integer> subPair =
                    computeLCS(s1, i1 + 1, s2, i2 + 1, sb, mem);

            sb.append(" Cp(" + common + ")");

            Pair<String, Integer> res = new Pair<String, Integer>(
                    String.format("%s%s", common, subPair.first),
                    1 + subPair.second.intValue());

            mem[i1][i2] = res;
            return res;
        }

        /*
         * Calculate the max of skipping the first character in either strings
         * one by one.
         */

        Pair<String, Integer> p1 = computeLCS(s1, i1 + 1, s2, i2, sb, mem);
        Pair<String, Integer> p2 = computeLCS(s1, i1, s2, i2 + 1, sb, mem);

        Pair<String, Integer> winner = null;
        if (p1.second >= p2.second) {
            sb.append(" Del(" + s1.charAt(0) + ")");
            winner = p1;
        } else if (p1.second < p2.second) {
            sb.append(" Ins(" + s2.charAt(0) + ")");
            winner = p2;
        }
//        else {
//            sb.append(" Rep(" + s1.charAt(0) + "-->" + s2.charAt(0) + ")");
//            winner = p2;
//        }

        mem[i1][i2] = winner;
        return winner;
    }

}
