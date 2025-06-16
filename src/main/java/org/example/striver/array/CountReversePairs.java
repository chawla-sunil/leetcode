package org.example.striver.array;

public class CountReversePairs {
//    Given an array of numbers, you need to return the count of reverse pairs.
//    Reverse Pairs are those pairs where i<j and arr[i]>2*arr[j].

    public int countReversePairs(int[] nums) {
        return mergeSortAndCount(nums, 0, nums.length - 1);
    }

    private int mergeSortAndCount(int[] nums, int left, int right) {
        if (left >= right) {
            return 0; // Base case: no pairs if the array has one or zero elements
        }
        int mid = left + (right - left) / 2;
        int count  = 0;
        count += mergeSortAndCount(nums, left, mid);
        count += mergeSortAndCount(nums, mid + 1, right);
        count += mergeAndCount(nums, left, mid, right);
        return count;
    }

    private int mergeAndCount(int[] nums, int left, int mid, int right) {
        int count = countPairs(nums, left, mid, right);

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

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                nums[k++] = leftArray[i++];
            } else {
                nums[k++] = rightArray[j++];
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

    public static int countPairs(int[] arr, int low, int mid, int high) {
        int right = mid + 1;
        int count = 0;
        for (int i = low; i <= mid; i++) {
            while (right <= high && arr[i] > 2 * arr[right]) right++;
            count += (right - (mid + 1));
        }
        return count;
    }

}
