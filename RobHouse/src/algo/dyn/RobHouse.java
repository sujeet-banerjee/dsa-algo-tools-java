/**
 *  House Robber
Medium

10127

228

Add to List

Share
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.



Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
 */
package algo.dyn;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author sujeet
 *
 */
public class RobHouse {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(
                "EX: " + (new RobHouse().rob(new int[] { 2, 7, 9, 3, 1 })));
    }

    // Don't make it static - else you are screwed!
    private Map<Integer, Integer> ROB = new HashMap<>();

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public int rob(int[] nums, int start) {

        if (start > nums.length) {
            throw new IllegalArgumentException(
                    "Start must not be > than the nums.length");
        }

        // EMPTY ==> No houses to rob!!
        if (nums.length == 0 || start == nums.length) {
            return 0;
        }

        // ONLY one house to rob ==> **** it!
        if (start == nums.length - 1) {
            return nums[start];
        }

        // TWO houses to rob! Eenee-Meenee-Miineee-Mo...
        if (nums.length - start == 2) {
            // Return the max
            return max(nums[start], nums[nums.length - 1]);
        }

        if (ROB.containsKey(start)) {
            return ROB.get(start);
        }

        int ret = max(nums[start] + rob(nums, start + 2),
                nums[start + 1] + rob(nums, start + 3));
        ROB.put(start, ret);
        if (ret < 0) {
            throw new IllegalStateException("Arithmetic Overflow!! start="
                    + start + " INP=" + Arrays.toString(nums));
        }

        return ret;
    }

    public int rob(int[] nums) {
        return rob(nums, 0);
    }

}
