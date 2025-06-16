package org.example.striver.array;

public class S3LC283MoveZeroes {
//    Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
//
//    Note that you must do this in-place without making a copy of the array.
//
//
//
//    Example 1:
//
//    Input: nums = [0,1,0,3,12]
//    Output: [1,3,12,0,0]
//    Example 2:
//
//    Input: nums = [0]
//    Output: [0]


    public void moveZeroes(int[] nums) {
        int left = 0; // refering to the index of 0
        for(int i = 0; i < nums.length; i++) {
            // we are swapping the 0 with non zero
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[left];
                nums[left] = temp;
                left++;
            }
        }
    }

    // Approach 2 will be to first move the non-zero to start and then put 0 in the end.

    // public void moveZeroes(int[] nums) {
    //     if (nums == null || nums.length == 0) return;
    //     int i = 0;
    //     for (int num: nums) {
    //         if (num != 0) nums[i++] = num;
    //     }

    //     while (i < nums.length) {
    //         nums[i++] = 0;
    //     }
    // }

}
