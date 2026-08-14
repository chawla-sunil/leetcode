package org.example.companies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class FeaturelyAI {
    // 4 Aug 2026
//    Q1 : Merge Intervals
//    Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
//
//    Example 1:
//    Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//    Output: [[1,6],[8,10],[15,18]]
//    Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
//
//    Example 2:
//    Input: intervals = [[1,4],[4,5]]
//    Output: [[1,5]]
//    Explanation: Intervals [1,4] and [4,5] are considered overlapping.
//
//            Example 3:
//    Input: intervals = [[4,7],[1,4]]
//    Output: [[1,7]]
//    Explanation: Intervals [1,4] and [4,7] are considered overlapping.
//
//
//            Constraints:
//            1 <= intervals.length <= 104
//    intervals[i].length == 2
//            0 <= starti <= endi <= 104


    public static int[][] solve(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] last = res.get(res.size() - 1);

            if (intervals[i][0] <= last[1]) {
                last[1] = Math.max(last[1], intervals[i][1]);
            } else {
                res.add(intervals[i]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }


//    Q2: Word Ladder
//    A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
//    Every adjacent pair of words differs by a single letter.
//    Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
//    sk == endWord
//    Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
//
//    Example 1:
//    Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
//    Output: 5
//    Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
//
//    Example 2:
//    Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
//    Output: 0
//    Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
//
//
//    Constraints:
//            1 <= beginWord.length <= 10
//    endWord.length == beginWord.length
//1 <= wordList.length <= 5000
//    wordList[i].length == beginWord.length
//    beginWord, endWord, and wordList[i] consist of lowercase English letters.
//            beginWord != endWord
//    All the words in wordList are unique.

    public static int solve2(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level = 1;
        while (!queue.isEmpty()) {
            int size  = queue.size();

            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return level;
                }

                char[] chars = word.toCharArray();

                for (int j = 0; j < chars.length; j++) {
                    char old = chars[j];
                    for (char c ='a'; c <= 'z'; c++) {
                        chars[j] = c;
                        String next = new String(chars);

                        if (dict.contains(next)) {
                            queue.offer(next);
                            dict.remove(next);
                        }
                    }

                    chars[j] = old;
                }
            }

            level++;
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Start small. Ship something.");
        int[][] arr = new int[2][2];
        // [[1,3],[2,6],[8,10],[15,18]]

        arr[0] = new int[]{4, 7};
        arr[1] = new int[]{1, 4};
        // arr[2] = new int[]{8, 10};
        // arr[3] = new int[]{15, 18};

        int[][] result = solve(arr);
        System.out.println(Arrays.deepToString(result));

        int res2 = solve2("hit", "cog", List.of("hot","dot","dog","lot","log"));
        System.out.println(res2);
    }
}
