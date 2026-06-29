package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.List;

public class LC212WordSearchII {
//    Given an m x n board of characters and a list of strings words, return all words on the board.
//
//    Each word must be constructed from letters of sequentially adjacent cells,
//    where adjacent cells are horizontally or vertically neighboring.
//    The same letter cell may not be used more than once in a word.
//
//
//
//    Example 1:
//    Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
//    Output: ["eat","oath"]
//
//
//    Example 2:
//    Input: board = [["a","b"],["c","d"]], words = ["abcb"]
//    Output: []
//
//
//    Constraints:
//
//    m == board.length
//    n == board[i].length
//    1 <= m, n <= 12
//    board[i][j] is a lowercase English letter.
//    1 <= words.length <= 3 * 104
//    1 <= words[i].length <= 10
//    words[i] consists of lowercase English letters.
//    All the strings of words are unique.

    // we will iterate over the board cells and check which all words we can make
    // from the words array. To check what all words have the same starting point,
    // We create a Trie, where we store these words and at the end of the word,
    // we store the word, so we know we have found one word.
    // Once we have found the word, we will add that word our result set.
    // once a character is found for board[i][j] in the trie node,
    // then we move to next character in the trie and move to
    // next adjacent board cells which are horizontally or vertically neighboring
    // and mark the board[i][j] = #, to track it as it is visited,
    // we don't count this in next character finding in dfs
    public List<String> findWords(char[][] board, String[] words) {
        Trie root = buildTrie(words);
        List<String> res = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, board, root, res);
            }
        }

        return res;
    }

    public void dfs(int i, int j, char[][] board, Trie root, List<String> res) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }

        char c = board[i][j];
        if (c == '#' || root.children[c - 'a'] == null) {
            return;
        }

        if (root.children[c - 'a'].word != null) {
            // Set it to null to avoid duplicates (standard optimization)
            // if we have found a word, we are gonna add that word
            // mark that word as null in the trie, so we do not visit it again
            // and so we do not add it again the list.
            // To avoid this, we could store word in a set also,
            // and we would not need to mark word = null
            // but this current is better as can directly return the list.
            // WE are not stopping and returning here, because there might be more character ahead of this.
            // Example words: [to, toy, toys]
            res.add(root.children[c - 'a'].word);
            root.children[c - 'a'].word = null;
        }

        root = root.children[c - 'a'];
        board[i][j] = '#';

        if (i > 0) dfs(i - 1, j, board, root, res);
        if (j > 0) dfs(i, j - 1, board, root, res);
        if (i < board.length - 1)    dfs(i + 1, j, board, root, res);
        if (j < board[0].length - 1) dfs(i, j + 1, board, root, res);

        board[i][j] = c;
    }

    public Trie buildTrie(String[] words) {
        Trie root = new Trie();

        for (String word: words) {
            Trie curr = root;
            for (char c: word.toCharArray()) {
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new Trie();
                }
                curr = curr.children[c - 'a'];
            }
            curr.word = word; // marking end of word
        }
        return root;
    }

    private class Trie {
        Trie[] children = new Trie[26];
        String word;
    }
}
