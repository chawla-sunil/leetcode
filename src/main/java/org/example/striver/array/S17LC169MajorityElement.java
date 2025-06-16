package org.example.striver.array;

import java.util.HashMap;
import java.util.Map;

public class S17LC169MajorityElement {
//    Given an array nums of size n, return the majority element.
//
//    The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

//    Example 1:
//    Input: nums = [3,2,3]
//    Output: 3
//
//    Example 2:
//    Input: nums = [2,2,1,1,1,2,2]
//    Output: 2

    public int majorityElement3(int[] nums) {
        int count = 0;
        int element = 0;

        for (int num: nums) {
            if (count == 0) {
                count++;
                element = num;
            } else if  (num == element) {
                count++;
            } else {
                count--;
            }
        }

        return element;
    }

    // this one and majorityElement3 are same solution but majorityElement3 is more genreric and
    // written in better way. but somehow this one beats 99.84% and majorityElement3 beats 75.02%, weird.
    public int majorityElement(int[] nums) {
        int count = 0;
        int element = 0;

        for (int num: nums) {
            if (count == 0) {
                element = num;
            }

            if (num == element) {
                count++;
            } else{
                count--;
            }
        }

        return element;
    }

    public int majorityElement2(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }


        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() > (n)/2) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
