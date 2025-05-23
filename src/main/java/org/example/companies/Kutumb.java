package org.example.companies;

import java.util.Arrays;

public class Kutumb {
//    Move all zeroes to end of array
//    Input :  arr[] = {1, 2, 0, 4, 3, 0, 5, 0};
//    Output : arr[] = {1, 2, 4, 3, 5, 0, 0, 0};
//
//    Input : arr[]  = {1, 2, 0, 0, 0, 3, 6};
//    Output : arr[] = {1, 2, 3, 6, 0, 0, 0};
//    https://www.geeksforgeeks.org/move-zeroes-end-array/
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 0, 4, 3, 0, 5, 0};
        int[] out = shiftArr(arr);
        System.out.println(Arrays.toString(out));
     }
     public static int[] shiftArr(int[] arr) {
        int  n = arr.length;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                int aI = arr[i];
                arr[i] = arr[j];
                arr[j] = aI;
                j++;
            }
        }
        return arr;
     }
}
