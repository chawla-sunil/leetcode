package org.example.top_interview_questions;

import java.util.Stack;

public class LC130SurroundedRegions {
//    You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
//
//    Connect: A cell is connected to adjacent cells horizontally or vertically.
//    Region: To form a region connect every 'O' cell.
//            Surround: A region is surrounded if none of the 'O' cells in that region are on the edge of the board. Such regions are completely enclosed by 'X' cells.
//    To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.
//
//
//
//    Example 1:
//    Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
//    Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//    Explanation:
//    In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.
//
//
//    Example 2:
//    Input: board = [["X"]]
//    Output: [["X"]]
//
//
//
//    Constraints:
//
//    m == board.length
//    n == board[i].length
//    1 <= m, n <= 200
//    board[i][j] is 'X' or 'O'.




    // both solutions are good.
    // first is recursive and fast
    // second is iterative with Stack

    int m;
    int n;
    // We first check all four borders
    // if the border has O then we do the dfs and mark visited true
    // then we traverse board and visited
    // mark X everywhere where board cell is O and visited is false
    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        // visit first and last column
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O' && !visited[i][0]) {
                dfs(i, 0, board, visited);
            }
            if (board[i][n-1] == 'O' && !visited[i][n-1]) {
                dfs(i, n-1, board, visited);
            }
        }

        // visit first and last row
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O' && !visited[0][j]) {
                dfs(0, j, board, visited);
            }
            if (board[m-1][j] == 'O' && !visited[m-1][j]) {
                dfs(m-1, j, board, visited);
            }
        }

        // replace O with X if the O is visited
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(int i, int j, char[][] board, boolean[][] visited) {
        if (i<0 || j<0 || i>=m || j>=n || board[i][j]=='X' || visited[i][j]) {
            return;
        }

        visited[i][j] = true;
        dfs(i, j+1, board, visited);
        dfs(i+1, j, board, visited);
        dfs(i, j-1, board, visited);
        dfs(i-1, j, board, visited);
    }



    // both are good solution, this is iterative solution with Stack
    public void solve2(char[][] board) {
        m = board.length;
        n = board[0].length;

        Stack<Pair> stack = new Stack<>();
        boolean[][] visited = new boolean[m][n];
        // instead of creating new visited array,
        // we can replace O with * when we visit a node/cell
        // and once stack is empty, in the last
        // we can replace * with O.

        // visit first and last column
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                stack.add(new Pair(i, 0));
                visited[i][0] = true;
            }
            if (board[i][n-1] == 'O') {
                stack.add(new Pair(i, n-1));
                visited[i][n-1] = true;
            }
        }

        // visit first and last row
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                stack.add(new Pair(0, j));
                visited[0][j] = true;
            }
            if (board[m-1][j] == 'O') {
                stack.add(new Pair(m-1, j));
                visited[m-1][j] = true;
            }
        }

        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            int row = pair.row;
            int col = pair.col;

            int[] deltaRow = {0, 1, 0, -1};
            int[] deltaCol = {1, 0, -1, 0};

            int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            for (int i = 0; i < deltaRow.length; i++) {
                int nRow = row + deltaRow[i];
                int nCol = col + deltaCol[i];

                if (nRow>=0 && nCol>=0 && nRow<m && nCol<n && board[nRow][nCol] == 'O' && !visited[nRow][nCol]) {
                    stack.add(new Pair(nRow, nCol));
                    visited[nRow][nCol] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
