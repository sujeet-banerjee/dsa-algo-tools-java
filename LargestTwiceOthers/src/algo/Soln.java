package algo;

/**
 * Largest Number At Least Twice of Others Easy
 * 
 * 576
 * 
 * 720
 * 
 * Add to List
 * 
 * Share You are given an integer array nums where the largest integer is
 * unique.
 * 
 * Determine whether the largest element in the array is at least twice as much
 * as every other number in the array. If it is, return the index of the largest
 * element, or return -1 otherwise.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [3,6,1,0] Output: 1 Explanation: 6 is the largest integer. For
 * every other number in the array x, 6 is at least twice as big as x. The index
 * of value 6 is 1, so we return 1. Example 2:
 * 
 * Input: nums = [1,2,3,4] Output: -1 Explanation: 4 is less than twice the
 * value of 3, so we return -1. Example 3:
 * 
 * Input: nums = [1] Output: 0 Explanation: 1 is trivially at least twice the
 * value as any other number because there are no other numbers.
 * 
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 50 0 <= nums[i] <= 100 The largest element in nums is
 * unique.
 *
 * @author sujeet
 *
 */
public class Soln {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public int dominantIndex(int[] nums) {

        if (nums.length == 1) {
            return 0;
        }

        if (nums.length == 2) {
            return nums[0] >= 2 * nums[1] ? 0 : nums[0] * 2 <= nums[1] ? 1 : -1;
        }

        int largest = nums[0] > nums[1] ? 0 : 1;
        int prevLargest = nums[0] > nums[1] ? 1 : 0;

        // O(n) Algo to find both ranks - 0 and 1
        // (i.e. largest and second-largest)
        for (int i = 2; i < nums.length; i++) {

            if (nums[prevLargest] < nums[i]) {
                prevLargest = i;
            }

            if (nums[largest] < nums[i]) {
                prevLargest = largest;
                largest = i;
            }
        }

        return nums[largest] >= 2 * nums[prevLargest] ? largest : -1;

    }

}

