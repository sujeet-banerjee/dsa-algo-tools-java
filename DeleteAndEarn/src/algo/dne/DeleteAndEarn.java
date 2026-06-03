package algo.dne;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

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
public class DeleteAndEarn {

    public static void main(String[] args) {
        int[] nums = new int[] { 2, 2, 3, 3, 3, 4 };

        int[] nums2 = new int[] { 12, 32, 93, 17, 100, 72, 40, 71, 37, 92, 58,
                34, 29, 78, 11, 84, 77, 90, 92, 35, 12, 5, 27, 92, 91, 23, 65,
                91, 85, 14, 42, 28, 80, 85, 38, 71, 62, 82, 66, 3, 33, 33, 55,
                60, 48, 78, 63, 11, 20, 51, 78, 42, 37, 21, 100, 13, 60, 57, 91,
                53, 49, 15, 45, 19, 51, 2, 96, 22, 32, 2, 46, 62, 58, 11, 29, 6,
                74, 38, 70, 97, 4, 22, 76, 19, 1, 90, 63, 55, 64, 44, 90, 51,
                36, 16, 65, 95, 64, 59, 53, 93 };
        int val = new DeleteAndEarn().deleteAndEarn(nums2);
        System.out.println("Earned: " + val);
    }

    private Map<Set<Integer>, Integer> MEM = new HashMap<>();
    private Map<Integer, Integer> VAL = new HashMap<>();

    public int deleteAndEarn(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (VAL.containsKey(num)) {
                VAL.put(num, (VAL.get(num) + num));
            } else {
                VAL.put(num, num);
            }
        }

        System.out.println("Starting Num Set: " + VAL.keySet());
        System.out.println("Starting Val Map: " + VAL);
        // System.out.println("Test Max: " + max(20, 300, 6, 100));
        return dAndE(VAL.keySet());
    }

    public int max(int... nums) {
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

    public int dAndE(Set<Integer> set) {
        System.out.println("EXEC for set="+ set);
        if (set.isEmpty()) {
            return 0;
        }

        if (set.size() == 1) {
            return VAL.get(set.toArray(new Integer[] {})[0]);
        }

        if (MEM.containsKey(set)) {
            return MEM.get(set);
        }

        Set<Integer> copy1 = new HashSet<>(set);
        Set<Integer> copy2 = new HashSet<>(set);

        // Case 1: pick x, so x-1 and x+1 are excluded from nums
        int x = set.toArray(new Integer[] {})[0];
        copy1.remove(x);
        copy1.remove(x + 1);
        copy1.remove(x - 1);
        int earn1 = VAL.get(x) + dAndE(copy1);

        // Case 2: exclude x and recurse on the set (nums - x)
        copy2.remove(x);
        int earn2 = dAndE(copy2);

        int ret = max(earn1, earn2);
        MEM.put(set, ret);
        return ret;
    }

}
