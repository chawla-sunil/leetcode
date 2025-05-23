package org.example.companies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Porter {
    public static void main(String[] args) {
        // question 1
        // find triplet with sum = given target value;
        int[] arr = {1,2,3,5,6,7};
        List<List<Integer>> output = triplet(arr, 10);
        System.out.println(output);



        // question 2
        // given an array find list of sub-array with max sum and having k elements
        // result = [[2, 3, 5], [5, 1, 4], [1, 4, 5]]  = sum = 10 and k = 3(no. of elements in each sub array)
        int[] arr2 = { 1,2,3,5,1,4,5 };
        List<List<Integer>> out2 = kadaneTwist(arr2, 3);
        System.out.println(out2);
    }
    public static List<List<Integer>> triplet(int[] arr, int k) {
        int n = arr.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = i +1;
            int end = n - 1;
            while (start < end) {
                int sum = arr[i] + arr[start] + arr[end];

                if (sum == k) {
                    res.add(Arrays.asList(arr[i], arr[start], arr[end]));
                    start++;
                    end--;
                } else if (sum < k) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return res;
    }

    public static List<List<Integer>> kadaneTwist(int[] arr, int k) {
        int maxSum = 0;
        List<List<Integer>> out = new ArrayList<>();
        int n = arr.length;


        for (int i = 0; i + k <= n; i++) {
            int temSum = 0;
            for (int j = i; j < i + k; j++) {
                temSum += arr[j];
            }
            if (temSum == maxSum) {
                List<Integer> temp = new ArrayList<>();
                for (int j=i; j < i+k; j++) {
                    temp.add(arr[j]);
                }
                out.add(temp);
            }
            if (temSum > maxSum) {
                maxSum = temSum;
                out.clear();
                List<Integer> temp = new ArrayList<>();
                for (int j=i; j < i+k; j++) {
                    temp.add(arr[j]);
                }
                out.add(temp);
            }
        }
        System.out.println(maxSum);
        return out;
    }
}
