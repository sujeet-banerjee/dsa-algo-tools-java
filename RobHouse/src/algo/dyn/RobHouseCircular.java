/**
 *
 */
package algo.dyn;

import java.util.Map;
import java.util.HashMap;

class Solution {
    // Memoization
    private Map<Integer, Map<Integer, Integer>> ROB = new HashMap<>();

    public int max(int... aa) {
        int maxx = aa[0];
        for (int i = 1; i < aa.length; i++) {
            if (maxx < aa[i]) {
                maxx = aa[i];
            }
        }
        return maxx;
    }

    public int robP(int[] nums, int start, int end) {
        if (start > end + 1) {
            throw new IllegalArgumentException("Start must be < End");
        }

        // NO House to rob!
        if (nums.length == 0 || start == end + 1) {
            return 0;
        }

        // Exactly one house in the locality - Grab it!
        if (end - start == 0) {
            return nums[start];
        }

        // Exactly two houses - get the max
        if (end - start == 1) {
            return max(nums[start], nums[end]);
        }

        if (ROB.containsKey(start)) {
            Map<Integer, Integer> map = ROB.get(start);
            if (map.containsKey(end)) {
                return map.get(end);
            }
        } else {
            ROB.put(start, new HashMap<>());
        }

        int ret = max(nums[start] + robP(nums, start + 2, end),
                nums[start + 1] + robP(nums, start + 3, end));

        ROB.get(start).put(end, ret);
        return ret;
    }

    public int rob(int[] nums, int start, int end) {
        if (start > end + 1) {
            throw new IllegalArgumentException("Start must be < End");
        }

        // NO House to rob!
        if (nums.length == 0 || start == end + 1) {
            return 0;
        }

        // Exactly one house in the locality - Grab it!
        if (end - start == 0) {
            return nums[start];
        }

        // Exactly two houses - get the max
        if (end - start == 1) {
            return max(nums[start], nums[end]);
        }

        // Exactly three houses - get the max
        if (end - start == 2) {
            return max(nums[start], nums[start + 1], nums[end]);
        }

        int ret = max(nums[start] + robP(nums, start + 2, end - 1),
                nums[start + 1] + robP(nums, start + 3, end),
                robP(nums, start + 1, end - 2) + nums[end]);
        return ret;
    }

    public int rob(int[] nums) {
        return rob(nums, 0, nums.length - 1);
    }
}
