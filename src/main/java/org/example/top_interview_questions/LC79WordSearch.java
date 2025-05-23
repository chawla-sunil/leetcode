package org.example.top_interview_questions;

public class LC79WordSearch {
//    Given an m x n grid of characters board and a string word, return true if word exists in the grid.
//    The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are
//    horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
//            Example 1:
//    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
//    Output: true
//
//    Example 2:
//    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
//    Output: true
//
//    Example 3:
//    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
//    Output: false
//
//
//    Constraints:
//
//            m == board.length
//            n = board[i].length
//            1 <= m, n <= 6
//            1 <= word.length <= 15
//            board and word consists of only lowercase and uppercase English letters.
//
//
//    Follow up: Could you use search pruning to make your solution faster with a larger board?

    // My Solution, good
    boolean[][] visited;
    public boolean exist1(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && search(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean search(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }
        int m = board.length;
        int n = board[0].length;

        if (i<0 || i>=m || j<0 || j>=n) {
            return false;
        }
        if (word.charAt(index) != board[i][j] || visited[i][j]) {
            return false;
        }

        visited[i][j] = true;
        if (search(board, word, i+1, j, index+1) ||
                search(board, word, i-1, j, index+1) ||
                search(board, word, i, j+1, index+1) ||
                search(board, word, i, j-1, index+1)
        ) {
            return true;
        }
        visited[i][j] = false;
        return false;
    }

    // Same Solution but with DFS in normal way
    int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};
    public boolean exist2(char[][] board, String word) {
        if (board==null ||board.length==0||board[0].length==0) return false;
        int m=board.length,n=board[0].length;
        boolean[][] visited=new boolean[m][n];
        for (int i=0;i< m;i++){
            for (int j=0;j<n;j++){
                if (dfs(board,visited,i,j,0,word)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, boolean[][] visited, int x, int y, int i, String word){
        int m=board.length,n=board[0].length;
        if (i==word.length()) return true;

        if(x<0||x>=m||y<0||y>=n) return false;
        if(visited[x][y]) return false;
        if(board[x][y]!=word.charAt(i)) return false;

        visited[x][y]=true;
        for (int[] dir: dirs){
            int x1=x+dir[0], y1=y+dir[1];
            if (dfs(board, visited, x1, y1, i+1, word)){
                return true;
            }
        }
        visited[x][y]=false;
        return false;
    }

    // We can Save the space complexity by this method
    // instead of using visited, we will replace all the visited characters with '#'
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (search3(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean search3(char[][]board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }
        if (i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word.charAt(index) ) {
            return false;
        }
        if (board[i][j] == '#') {
            return false;
        }
        char tmp = board[i][j];
        board[i][j] = '#';

        boolean found =
                search3(board, word, i-1, j, index+1) ||
                        search3(board, word, i+1, j, index+1) ||
                        search3(board, word, i, j-1, index+1) ||
                        search3(board, word, i, j+1, index+1);

        board[i][j] = tmp;
        return found;
    }
}
