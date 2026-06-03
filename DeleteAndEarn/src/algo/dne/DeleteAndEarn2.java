package algo.dne;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Delete and Earn
<<Medium>>

You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:

Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
Return the maximum number of points you can earn by applying the above operation some number of times.



Example 1:

Input: nums = [3,4,2]
Output: 6
Explanation: You can perform the following operations:
- Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
- Delete 2 to earn 2 points. nums = [].
You earn a total of 6 points.
Example 2:

Input: nums = [2,2,3,3,3,4]
Output: 9
Explanation: You can perform the following operations:
- Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
- Delete a 3 again to earn 3 points. nums = [3].
- Delete a 3 once more to earn 3 points. nums = [].
You earn a total of 9 points.


Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i] <= 104

 * @author sujeet
 *
 */
public class DeleteAndEarn2 {

    private final Map<Set<Integer>, Integer> MEM = new HashMap<>();
    private final Map<Integer, Integer> count = new HashMap<>();

    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length < 1) {
            throw new IllegalArgumentException(
                    "Input Array of numbers must not be null or empty");
        }
        calculateCount(nums);
        int earning = evalDnE(MEM, count.keySet());

        return earning;
    }

    /***
     * Evaluates earnings and caches the known results for optimization
     *
     * @param mem
     * @param count
     * @param key
     * @return
     */
    protected int evalDnE(Map<Set<Integer>, Integer> mem,
            Set<Integer> keySet) {

        if (keySet == null) {
            throw new IllegalArgumentException(
                    "The input keysset cannot be null");
        }

        if (mem.containsKey(keySet)) {
            return mem.get(keySet);
        }

        if (keySet.isEmpty()) {
            System.out.println("Empty Set; no earnings");
            return 0;
        }

        Integer key = keySet.toArray(new Integer[] {})[0];

        // Case 1 - pick the key
        int score1 = case1ChooseTheKey(mem, keySet, key);

        // Case 2 - ignore the key
        int score2 = case2IgnoreTheKey(mem, keySet, key);

        int earning = max(score1, score2);

        mem.put(keySet, earning);
        System.out.println(
                String.format(
                        "MAX_SCORE %s => %d \n", keySet, earning));
        return earning;
    }

    /**
     *
     * @param mem
     * @param keySet
     * @param key
     * @return
     */
    private int case2IgnoreTheKey(Map<Set<Integer>, Integer> mem,
            Set<Integer> keySet, Integer key) {
        Set<Integer> keySetCopy = makeCopy(keySet);
        keySetCopy.remove(key);
        int score = evalDnE(mem, keySetCopy);
        String msg = String.format(
                "Score with set %s, NOT choosing key=%d >> %d",
                keySet, key, score);
        System.out.println(msg);
        return score;
    }

    /**
     *
     * @param mem
     * @param keySet
     * @param key
     * @return
     */
    private int case1ChooseTheKey(Map<Set<Integer>, Integer> mem,
            Set<Integer> keySet, Integer key) {
        // Make a personal copy of the key-set.
        Set<Integer> keySetCopy = makeCopy(keySet);
        int score = count.get(key) * key;
        keySetCopy.remove(key);
        keySetCopy.remove(Integer.valueOf(key - 1));
        keySetCopy.remove(Integer.valueOf(key + 1));
        score += evalDnE(mem, keySetCopy);
        String msg = String.format(
                "Score with set %s, choosing key=%d >> %d",
                keySet, key, score);
        System.out.println(msg);
        return score;
    }

    protected Set<Integer> makeCopy(Set<Integer> src) {
        if (src == null) {
            throw new IllegalArgumentException("Source keyset cannot be null");
        }
        return new HashSet<>(src);
    }

    /**
     *
     * @param nums
     * @return
     */
    protected Map<Integer, Integer> calculateCount(int[] nums) {

        for (int num : nums) {
            if (this.count.containsKey(num)) {
                this.count.put(num, count.get(num) + 1);
            } else {
                this.count.put(num, 1);
            }
        }
        return this.count;
    }


    public static int max(int... nums) {
        if (nums.length < 2) {
            throw new IllegalArgumentException(
                    "Numbers cannot be less than 2, for finding max.");
        }
        int max = nums[0];
        for (int num : nums) {
            if (max < num) {
                max = num;
            }
        }
        return max;
    }
}
