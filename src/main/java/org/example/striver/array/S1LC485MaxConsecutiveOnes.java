package org.example.striver.array;

public class S1LC485MaxConsecutiveOnes {
//    Given a binary array nums, return the maximum number of consecutive 1's in the array.
//
//
//
//    Example 1:
//
//    Input: nums = [1,1,0,1,1,1]
//    Output: 3
//    Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
//    Example 2:
//
//    Input: nums = [1,0,1,1,0,1]
//    Output: 2
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int curr = 0;
        for (int el: nums) {
            curr++;
            if (el == 0) {
                curr = 0;
            }
            max = curr > max ? curr : max;
        }
        return max;
    }
}
