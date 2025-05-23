package org.example.topInterviewQuestions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class LC127WordLadder {
//    A transformation sequence from word beginWord to word endWord using a dictionary wordList is a
//    sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
//
//    Every adjacent pair of words differs by a single letter.
//    Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
//    sk == endWord
//    Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in
//    the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
//
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
//
//    1 <= beginWord.length <= 10
//    endWord.length == beginWord.length
//    1 <= wordList.length <= 5000
//    wordList[i].length == beginWord.length
//    beginWord, endWord, and wordList[i] consist of lowercase English letters.
//    beginWord != endWord
//    All the words in wordList are unique.


    // It is actually a BFS question. Here is the flow => 
    // begig = "hit",       end = "cog",        list = ["hot","dot","dog","lot","log","cog"]

    // set = ["hot","dot","dog","lot","log","cog"]. acts as visited array but in opposite style
    // set will store all the word that has not been visited. 

    // we first going to remove beginWord("hit") from set, because it is the first word we visited. 

    // At every level we are just going to change every word from 'a' to 'z' at every position. 
    // and check if that word exist or not in set. if it exists in set that means it has not been 
    // visited, so we will add it to the queue.

    //   level 1           hit  
    //                      ⬇
    //   level 2           hot
    //                     ↙️   ↘️
    //   level 3        dot    lot                 (**)
    //                 ↙️   ↘️   ↙️   ↘️
    //   level 4    dog            log
    //                              ↘️
    //   level 5                    cog

    // so the answer is 5

    // (**) => at level 3 we can generate hot also from lot but since we have already visited hot at 
    // level 2 and we removed it there so hot is not in level 4.

    // At any level, if we find the endWord, then we will return the level of that word.

    // if we nevenr found out the endWorld that means it does not exists in the given list
    // so in the end we will return 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList); // it will store all the words which are not visited
        if (!set.contains(endWord)) {
            return 0;
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));

        set.remove(beginWord);

        while(!q.isEmpty()) {
            String word = q.peek().word;
            int level = q.peek().level;
            q.poll();

            if (word.equals(endWord)) {
                return level;
            }
            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char[] charArray = word.toCharArray();
                    charArray[i] = ch;

                    String newWord = new String(charArray);

                    if (set.contains(newWord)) {
                        set.remove(newWord);
                        q.add(new Pair(newWord, level + 1));
                    }
                }
            }
        }
        return 0;
    }

    private class Pair {
        String word;
        int level;

        Pair(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }
}
