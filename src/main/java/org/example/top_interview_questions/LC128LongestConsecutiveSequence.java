package org.example.top_interview_questions;

import java.util.HashSet;
import java.util.Set;

public class LC128LongestConsecutiveSequence {
//    Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
//
//    You must write an algorithm that runs in O(n) time.
//
//
//    Example 1:
//    Input: nums = [100,4,200,1,3,2]
//    Output: 4
//    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
//
//    Example 2:
//    Input: nums = [0,3,7,2,5,8,4,6,0,1]
//    Output: 9
//
//
//    Constraints:
//
//            0 <= nums.length <= 10^5
//            -10^9 <= nums[i] <= 10^9


    // both are same solution.
    // In this one we are starting from the start of the sequence
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int res = 1;

        for (int num : nums) {
            // If the current element 'num' is the start of a sequence (no 'num-1' in 'set')
            // we are starting from the start of the sequence
            // in another(longestConsecutive2) solution, we are starting from the middle.
            if (!set.contains(num - 1)) {
                int curr = num;
                int count = 1;

                while (set.contains(curr + 1)) {
                    // no need to remove actually, it will work without it also
                    // but if we don't remove it then we will have to process the same range again
                    // in case if the we have double/duplicate entry, example =>
                    // suppose we have 1 1 2 2 3 3 in the array the max len will be 3 - (1 2 3)
                    // and we will process the same loop two time for 1.
                    // It is just optimisation which is important in time complexity
                    set.remove(curr);
                    curr++;
                    count++;
                }

                res = Math.max(res, count);
            }
        }

        return res;
    }

    // In this one we are starting from the middle of the sequence
    public int longestConsecutive2(int[] nums) {
        int res = 0;// answer len
        Set<Integer> set = new HashSet<>();
        for(int i:nums) set.add(i); // add all elements in a set, we dont require duplicates because - 
        //suppose we have 1 1 2 2 3 3 in the array the max len will be 3 - (1 2 3) doesnt matter how many times a number is present 
        for(int i:nums){
            // the idea is to assume that the present value "i" is the center of the sequence in which it is present 
            // then we will go left and right of it to find the length of its sequence
            // suppose we have 5 1 2 3 4 6 7 8, now the first element we have is 5
            int max = 1,prevVal = i-1,nextVal = i+1; // max is the current length as we have one elemet in the sequence that is 5
            // now for 5 prevVal = 4
            while(set.contains(prevVal)){ // if 4 is present in the set i.e. in the array
                max++; // increase the sequence len
                set.remove(prevVal--); // remove 4 and decremennt prevVal to 3 and continue 
            }
            // removing the elements because an element can only be part of only one consecutive sequence
            while(set.contains(nextVal)){ // same for the nextVal of the sequence
                max++;
                set.remove(nextVal++);
            }
            res = Math.max(res,max); // maintaining max len 
        }
        return res;
    }
}
