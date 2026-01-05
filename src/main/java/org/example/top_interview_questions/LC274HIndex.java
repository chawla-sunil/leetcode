package org.example.top_interview_questions;

public class LC274HIndex {
//    Given an array of integers citations where citations[i] is the number of citations
//    a researcher received for their ith paper, return the researcher's h-index.
//
//    According to the definition of h-index on Wikipedia:
//    The h-index is defined as the maximum value of h such that the given
//    researcher has published at least h papers that have each been cited at least h times.
//
//
//    Example 1:
//    Input: citations = [3,0,6,1,5]
//    Output: 3
//    Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
//    Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
//
//    Example 2:
//    Input: citations = [1,3,1]
//    Output: 1
//    Constraints:
//
//    n == citations.length
//    1 <= n <= 5000
//    0 <= citations[i] <= 1000

    // We will store the citations on a new array bucket and storing how many times a citation is there(count) and
    // We are creating a bucket of n+1, because h-index can not be greater than n because
    // h-index is => researcher has published at least h papers that have each been cited at least h times.
    // Max papers researcher has published is n which means max h-index can only be n, so any citations above n,
    // we will store it at index n in the new bucket array
    public int hIndex(int[] citations) {
        int n = citations.length;

        int[] bucket = new int[n+1];
        for (int c: citations) {
            if (c >= n) {
                bucket[n]++;
            } else {
                bucket[c]++;
            }
        }

        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += bucket[i];
            if (count >= i) {
                return i;
            }
        }

        return 0;
    }
}
