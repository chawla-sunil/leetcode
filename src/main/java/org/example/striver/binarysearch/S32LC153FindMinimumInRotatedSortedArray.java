package org.example.striver.binarysearch;

public class S32LC153FindMinimumInRotatedSortedArray {
//    Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
//    For example, the array nums = [0,1,2,4,5,6,7] might become:
//                                  [4,5,6,7,0,1,2] if it was rotated 4 times.
//                                  [0,1,2,4,5,6,7] if it was rotated 7 times.
//    Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array
//                                  [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
//
//    Given the sorted rotated array nums of unique elements, return the minimum element of this array.
//
//    You must write an algorithm that runs in O(log n) time.
//
//
//    Example 1:
//    Input: nums = [3,4,5,1,2]
//    Output: 1
//    Explanation: The original array was [1,2,3,4,5] rotated 3 times.
//
//    Example 2:
//    Input: nums = [4,5,6,7,0,1,2]
//    Output: 0
//    Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
//
//    Example 3:
//    Input: nums = [11,13,15,17]
//    Output: 11
//    Explanation: The original array was [11,13,15,17] and it was rotated 4 times.

    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if(nums[left]<=nums[right]) return nums[left];

            int prev = (mid - 1 + n) % n;
            int next = (mid + 1) % n;
            // Check if mid is the minimum element
            if (nums[mid] < nums[next] &&
                    nums[mid] < nums[prev]) {
                return nums[mid];
            }

            if (nums[mid] >= nums[left]) {
                // Left part is sorted, so the minimum is in the right part
                left = mid + 1;
            } else {
                // Right part is sorted, so the minimum is in the left part
                right = mid - 1;
            }
        }

        return -1;
    }


    public int findMin2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] <= nums[right]) {
                right = mid;
                // We are doing this because we dare saving the answer on left.
                // and we have left < right condition on while loop.
                // so we don't wanna the right to go on pivot point
                // Example: [5,9,2,4,6,8] => if mid is 2, then mid-1 will be 9 and we will be left with [5,9]
                // then we will ignore the solution. so we will keep the right on mid that is 2 in this case
            } else {
                left = mid + 1;
            }
        }

        return nums[left];
    }
}
