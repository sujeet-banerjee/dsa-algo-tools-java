/**
 * Median of Two Sorted Arrays
Hard

14734

1857

Add to List

Share
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).



Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.


Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000

-10^6 <= nums1[i], nums2[i] <= 10^6
 */
package algo;

import java.util.Arrays;

/**
 * @author sujeet
 *
 */
public class MedianOfSorted {


    public double recurMedian(
            int[] nums1, int[] nums2, 
            int start1, int end1,
            int start2, int end2) {

        if (end1 < start1 - 1) {
            throw new IllegalArgumentException(
                    "start1 cannot be greater than end1");
        }
        if (end2 < start2 - 1) {
            throw new IllegalArgumentException(
                    "start2 cannot be greater than end2");
        }

        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        int[] sm = len1 < len2 ? nums1 : nums2;
        int[] lg = len1 < len2 ? nums2 : nums1;
        int startSm = len1 < len2 ? start1 : start2;
        int endSm = len1 < len2 ? end1 : end2;
        int startLg = len1 < len2 ? start2 : start1;
        int endLg = len1 < len2 ? end2 : end1;
        int lenSm = len1 < len2 ? len1 : len2;
        int lenLg = len1 < len2 ? len2 : len1;

        System.out.println("\n---- SM ----");
        System.out.println("Array: " + Arrays.toString(sm));
        System.out.println("Range: " + startSm + " : " + endSm);
        System.out.println("Len: " + lenSm);
        System.out.println("\n---- LG ----");
        System.out.println("Array: " + Arrays.toString(lg));
        System.out.println("Range: " + startLg + " : " + endLg);
        System.out.println("Len: " + lenLg);

        // Special based case
        if (len1 + len2 < 5) {
            int[] merged = new int[len1 + len2];
            int i = 0;
            for (int n : nums1) {
                merged[i++] = n;
            }
            for (int n : nums2) {
                merged[i++] = n;
            }

            Arrays.sort(merged);
            boolean oddM = (len1 + len2) % 2 != 0;

            System.out.println("--> Merged Array: " + Arrays.toString(merged));

            // Return the merged median
            return oddM ? merged[(len1 + len2) / 2] * 1.0
                    : (merged[(len1 + len2) / 2]
                            + merged[(len1 + len2) / 2 - 1]) / 2.0;
        }

        double medianSm = -1;
        double medianLg = -1;
        // Median smaller
        boolean oddSm = lenSm % 2 != 0;
        if (lenSm > 0) {
            medianSm = oddSm ? sm[startSm + (lenSm) / 2] * 1.0
                    : (sm[startSm + (lenSm) / 2]
                            + sm[startSm - 1 + (lenSm) / 2]) / 2.0;
        }

        // Median larger
        boolean oddLg = lenLg % 2 != 0;
        if (lenLg > 0) {
            medianLg = oddLg ? lg[startLg + (lenLg) / 2]
                    : (lg[startLg + (lenLg) / 2]
                            + lg[startLg - 1 + (lenLg) / 2]) / 2.0;
        }

        System.out.println(">>\nMedian SM: " + medianSm);
        System.out.println("Median LG: " + medianLg);

        // Base cases
        if (lenSm == 0 && lenLg == 0) {
            throw new IllegalStateException(
                    "Cannot find median if both arrays are empty!");
        }
        if (lenSm == 0) {
            return medianLg;
        }
        if (lenLg == 0) {
            return medianSm;
        }

        int numElementsToDrop = lenSm / 2;
        System.out.println("Drop Count: " + numElementsToDrop);

        // Need to lose at least one elememnt on each side.
        if (numElementsToDrop == 0) {
            throw new IllegalStateException(
                    "Must drop off at least 1 element from the either ends!");
        }

        if (medianSm < medianLg) {
            return recurMedian(sm, lg, startSm + numElementsToDrop, endSm,
                    startLg, endLg - numElementsToDrop);
        }
        return recurMedian(sm, lg, startSm, endSm - numElementsToDrop,
                startLg + numElementsToDrop, endLg);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return recurMedian(nums1, nums2, 0, nums1.length - 1, 0,
                nums2.length - 1);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        MedianOfSorted algo = new MedianOfSorted();
        int[] num1 = { 1, 3 };
        int[] num2 = { 2 };
        double answer = algo.findMedianSortedArrays(num1, num2);
        System.out.println("\nFOUND MEDIAN: " + answer);
    }
}
