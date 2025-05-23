package org.example.companies;

public class TransformCoOrSearsHoldings {
    // Question 1 =>
    // https://www.geeksforgeeks.org/maximum-size-rectangle-binary-sub-matrix-1s/

//    Given a binary matrix, find the maximum size rectangle binary-sub-matrix with all 1’s
//    Input:
//            0 1 1 0
//            1 1 1 1
//            1 1 1 1
//            1 1 0 0
//    Output :
//            8
//    Explanation :
//    The largest rectangle with only 1's is from
//            (1, 0) to (2, 3) which is
//        1 1 1 1
//        1 1 1 1
//
//    Input:
//            0 1 1
//            1 1 1
//            0 1 1
//    Output:
//            6
//    Explanation :
//    The largest rectangle with only 1's is from
//            (0, 1) to (2, 2) which is
//        1 1
//        1 1
//        1 1

    // Answer Approach =>
//    we will use the maximum area histogram problem(Aditya Verma Youtube) using stack using
//    NSR(Nearest Smaller to Right) and NSL(Nearest Smallest to Left)
//    we will iterate over each row and make the histogram
//    and for each row we will calculate the maxAreaHistogram
    






    // Question 2 => Leetcode 33 =>
    // https://leetcode.com/problems/search-in-rotated-sorted-array/description/?envType=featured-list&envId=top-interview-questions?envType=featured-list&envId=top-interview-questions

//    There is an integer array nums sorted in ascending order (with distinct values).
//
//    Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
//
//    Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
//
//    You must write an algorithm with O(log n) runtime complexity.
//
//
//
//    Example 1:
//
//    Input: nums = [4,5,6,7,0,1,2], target = 0
//    Output: 4
//    Example 2:
//
//    Input: nums = [4,5,6,7,0,1,2], target = 3
//    Output: -1
//    Example 3:
//
//    Input: nums = [1], target = 0
//    Output: -1

    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while(low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[low] <= nums[mid]) { // at any point if left sub-half is sorted
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else { // at any point if right sub-half is sorted
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
