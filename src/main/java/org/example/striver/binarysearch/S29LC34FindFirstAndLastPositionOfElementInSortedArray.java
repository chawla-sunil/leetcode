package org.example.striver.binarysearch;

public class S29LC34FindFirstAndLastPositionOfElementInSortedArray {
//    Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
//    If target is not found in the array, return [-1, -1].
//
//    You must write an algorithm with O(log n) runtime complexity.
//
//
//    Example 1:
//    Input: nums = [5,7,7,8,8,10], target = 8
//    Output: [3,4]
//
//    Example 2:
//    Input: nums = [5,7,7,8,8,10], target = 6
//    Output: [-1,-1]
//
//    Example 3:
//    Input: nums = [], target = 0
//    Output: [-1,-1]


    // This algorithm in not completely O(log(N))
    // because we are not travesing left and right if we find mid.
    // It's good solution but for interview perspective
    public int[] searchRange(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        int start = -1;
        int end = -1;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                start = mid;
                while(start >= 0 && nums[start] == target) {
                    start--;
                }

                end = mid;
                while(end < nums.length && nums[end] == target) {
                    end++;
                }

                return new int[] {start + 1, end - 1};
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return new int[] {-1, -1};
    }

    // This algorithm is true O(log(N))
    public int[] searchRange2(int[] nums, int target) {
        int first = firstOccurrence(nums, target);
        if (first == -1) return new int[] { -1, -1};
        int end = lastOccurrence(nums, target);

        return new int[] {first, end};
    }

    public int firstOccurrence(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        int ans = -1;
        while(low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                ans = mid;
                high = mid - 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    public int lastOccurrence(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        int ans = -1;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                ans = mid;
                low = mid + 1;
            } else if (nums[mid] < target) {
                low = mid  + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

}
