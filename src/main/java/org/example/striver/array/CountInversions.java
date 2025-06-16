package org.example.striver.array;

public class CountInversions {
//    Given an array of N integers, count the inversion of the array (using merge-sort).
//
//    What is an inversion of an array? Definition:
//    for all i & j < size of array, if i < j then you have to find pair (A[i],A[j]) such that A[j] < A[i].


    // Main function to count inversions
    public int countInversions(int[] nums) {
        return mergeSortAndCount(nums, 0, nums.length - 1);
    }

    private int mergeSortAndCount(int[] nums, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            int count = 0;

            count += mergeSortAndCount(nums, left, mid);
            count += mergeSortAndCount(nums, mid + 1, right);
            count += mergeAndCount(nums, left, mid, right);

            return count;
        }
        return 0; // Base case: no inversions if the array has one or zero elements
    }

    private int mergeAndCount(int[] nums, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = nums[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = nums[mid + 1 + j];
        }

        int i = 0, j =0, k = left;
        int count = 0;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                nums[k++] = leftArray[i++];
            } else {
                nums[k++] = rightArray[j++];
                count += (n1 - i); // Count inversions
            }
        }
        while (i < n1) {
            nums[k++] = leftArray[i++];
        }
        while (j < n2) {
            nums[k++] = rightArray[j++];
        }

        return count;
    }

}
