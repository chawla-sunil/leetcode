package org.example.top_interview_questions;

public class LC4MedianOfTwoSortedArrays {
//    Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
//
//    The overall run time complexity should be O(log (m+n)).
//
//
//
//    Example 1:
//
//    Input: nums1 = [1,3], nums2 = [2]
//    Output: 2.00000
//    Explanation: merged array = [1,2,3] and median is 2.
//    Example 2:
//
//    Input: nums1 = [1,2], nums2 = [3,4]
//    Output: 2.50000
//    Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
//
//
//    Constraints:
//
//            nums1.length == m
//            nums2.length == n
//            0 <= m <= 1000
//            0 <= n <= 1000
//            1 <= m + n <= 2000
//            -10^6 <= nums1[i], nums2[i] <= 10^6

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int low = 0;
        int high = m;
        int realMid = (m + n + 1) / 2;

        while (low <= high) {
            int cut1 = (low + high) / 2;
            int cut2 = realMid - cut1;

            int leftA = cut1 > 0 ? nums1[cut1 - 1] : Integer.MIN_VALUE;
            int leftB = cut2 > 0 ? nums2[cut2 - 1] : Integer.MIN_VALUE;

            int rightA = cut1 < m ? nums1[cut1] : Integer.MAX_VALUE;
            int rightB = cut2 < n ? nums2[cut2] : Integer.MAX_VALUE;

            if (leftA <= rightB && leftB <= rightA) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(leftA, leftB) + Math.min(rightA, rightB)) / 2.0;
                }
                return Math.max(leftA, leftB);
            } else if (leftA > rightB) {
                high = cut1 - 1;
            } else if (leftB > rightA) {
                low = cut1 + 1;
            }
        }
        return 0.0;
    }
}
