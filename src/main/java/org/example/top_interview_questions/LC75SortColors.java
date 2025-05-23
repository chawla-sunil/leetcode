package org.example.top_interview_questions;

public class LC75SortColors {

//    Given an array nums with n objects colored red, white, or blue, sort them in-place so that
//    objects of the same color are adjacent, with the colors in the order red, white, and blue.
//
//    We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
//
//    You must solve this problem without using the library's sort function.
//
//    Example 1:
//    Input: nums = [2,0,2,1,1,0]
//    Output: [0,0,1,1,2,2]
//
//    Example 2:
//    Input: nums = [2,0,1]
//    Output: [0,1,2]
//
//
//    Constraints:
//
//    n == nums.length
//    1 <= n <= 300
//    nums[i] is either 0, 1, or 2.
//
//
//    Follow up: Could you come up with a one-pass algorithm using only constant extra space?

    public void sortColors(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        for (int i = 0; i <= high;) {
            if (nums[i] == 0) {
                swap(nums, i++, low++);
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, high--);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void sortColors2(int[] nums) {
        int[] arr = new int[3];
        for (int i : nums){
            arr[i]++;
        }
        int i = 0;
        while (arr[0]>0){
            nums[i++] = 0;
            arr[0]--;
        }
        while (arr[1]>0){
            nums[i++] = 1;
            arr[1]--;
        }
        while (arr[2]>0){
            nums[i++] = 2;
            arr[2]--;
        }
    }

    public void sortColors3(int[] nums) {
        int low=0;
        int high = nums.length-1;
        int mid=0;
        while(mid<=high) {
            if(nums[mid]==0) {
                int temp = nums[mid];
                nums[mid] = nums[low];
                nums[low] = temp;
                low++;  mid++;
            } else if(nums[mid]==1){
                mid++;
            } else {
                int temp =nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }
}
