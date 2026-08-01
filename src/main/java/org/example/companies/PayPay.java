package org.example.companies;

import java.util.Arrays;

public class PayPay {
    // 1 Aug 2026, PayPay

//        You are given two positive integer arrays spells and potions, of length n and m respectively,
//        where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
//                You are also given an integer success. A spell and potion pair is considered successful
//                if the product of their strengths is at least success.
//                Return an integer array pairs of length n where pairs[i] is the number of potions
//                that will form a successful pair with the ith spell.
//
//                Input: spells = [3,1,2], potions = [8,5,8], success = 16
//        Output: [2,0,2]
//        Explanation:
//        0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
//        1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful.
//        2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful.
//        Thus, [2,0,2] is returned.



    // spell * potion >= success
    // Psuedo code:
    // Sort potion
    // for (spell) {
    // l = 0;
    // r = n-1;
    // while (left <= right){
    // mid,
    // }
    // }

    public int[] solve(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = potions.length;
        int[] ans = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {
            int left = 0;
            int right = n - 1;
            int index = 0;

            while (left <= right) {
                int mid = left +  (right - left) / 2;
                long product = (long) spells[i] * potions[mid];
                if (product >= success) {
                    right = mid - 1;
                    index = mid;
                } else {
                    left = mid + 1;
                }
            }
            ans[i] = n - index;
        }

        return ans;
    }

    // What is DB index and how does it work
    // How to optimise an API
    // What are the ACID properties
    // 80 percent cache and 20 percent DB calls normally, All cache data is deleted,
    // What are the next stops to prevent all 100 percent calls to DB
    // How to decide kafka partition size
    // a: updateBD -> kafka, b kafka -> DB, B is not able to consume, how to check it
    // what is the difference between optimistic and pessimistic locking
    // how to choose DB SQL/NOSQL

}
