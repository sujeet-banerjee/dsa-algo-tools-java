package algo;

import java.util.Map;
import java.util.HashMap;
/**
 * PROBLEM:
 * 
 * 746. Min Cost Climbing Stairs
Easy

4890

906

Add to List

Share
You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the floor.

 

Example 1:

Input: cost = [10,15,20]
Output: 15
Explanation: You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.
Example 2:

Input: cost = [1,100,1,1,1,100,1,1,100,1]
Output: 6
Explanation: You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.
 

Constraints:

2 <= cost.length <= 1000
0 <= cost[i] <= 999
 * 
 * @author sujeet
 *
 */
class Solution {
    
    

    // 'Static' will likely cause wrong output as the Runner in teh platform
    // might be loading the class only once => the Map might already have
    // garbage.
    // private static Map<Integer, Integer> CST = new HashMap<>();
    private Map<Integer, Integer> CST = new HashMap<>();

    public static int min(int a, int b) {
        return a <= b ? a : b;
    }

    public int calcCost(int[] cost, int start) {
        if (cost == null) {
            throw new IllegalArgumentException("Cost array cannot be NULL.");
        }

        if (start > (cost.length)) {
            throw new IllegalStateException(
                    "Start index cannot be > cost.length. cost.length="
                            + cost.length);
        }

        if (cost.length == 0 || cost.length == start) {
            // You are already at the top!
            return 0;
        }

        if (cost.length - 1 == start) {
            return 0;
        }

        if (CST.containsKey(start)) {
            return CST.get(start);
        }

        int ret = min(cost[start] + calcCost(cost, start + 1),
                cost[start + 1] + calcCost(cost, start + 2));
        CST.put(start, ret);
        return ret;
    }

    public int minCostClimbingStairs(int[] cost) {

        return calcCost(cost, 0);

    }
}
