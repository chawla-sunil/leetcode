package org.example.striver.binarysearch;

public class S33LC540SingleElementInASortedArray {
//    You are given a sorted array consisting of only integers where every element appears exactly twice,
//    except for one element which appears exactly once.
//
//    Return the single element that appears only once.
//
//    Your solution must run in O(log n) time and O(1) space.
//
//
//    Example 1:
//    Input: nums = [1,1,2,3,3,4,4,8,8]
//    Output: 2
//
//    Example 2:
//    Input: nums = [3,3,7,7,10,11,11]
//    Output: 10

    // since every element appears twice, so for any element at an even index (0-indexed), the next element should be the same.
    // Similarly, for any element at an odd index, the previous element should be the same
    // If the middle element is at an even index, then the single element must be on the right side of the array,
    // since all the elements on the left side should come in pairs.
    // Similarly, if the middle element is at an odd index, then the single element must be on the left side of the array.
    // NOTE: this algorithm will still work even if the array isn't fully sorted.
    // As long as pairs are always grouped together in the array (for example, [10, 10, 4, 4, 7, 11, 11, 12, 12, 2, 2])
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // these 3 conditions are added because of the 1st if condition in the while loop
        if(right==0) {
            return nums[0];
        }
        if (nums[0] != nums[1]) {
            return nums[0];
        }
        if(nums[right]!=nums[right - 1])
            return nums[right];

        while(left <= right) {
            int mid = left + (right - left) / 2;

            // check if the middle elemnent is the answer
            if (nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1]) {
                return nums[mid];
            } else if ((mid % 2 == 0 && nums[mid] == nums[mid+1]) || (mid % 2 == 1 && nums[mid] == nums[mid-1])) {
                // if this is true that mean left part has all element in pairs
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        return -1;
    }
}
