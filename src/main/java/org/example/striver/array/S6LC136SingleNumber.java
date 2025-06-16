package org.example.striver.array;

public class S6LC136SingleNumber {
//    Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
//    You must implement a solution with a linear runtime complexity and use only constant extra space.

//    Example 1:
//    Input: nums = [2,2,1]
//    Output: 1
//
//    Example 2:
//    Input: nums = [4,1,2,1,2]
//    Output: 4
//
//    Example 3:
//    Input: nums = [1]
//    Output: 1

    public int singleNumber(int[] nums) {
        // x ^ x = 0;
        // x ^ 0 = x
        // ^ => XOR
        // same numbers will cancel out and only single will remain
        int result = 0;
        for (int num : nums) {
            result ^= num; // XOR operation
        }
        return result;
    }


}
