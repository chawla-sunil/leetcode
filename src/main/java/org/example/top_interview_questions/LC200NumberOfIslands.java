package org.example.top_interview_questions;

import java.util.Stack;

public class LC200NumberOfIslands {
//    Given an m x n 2D binary grid grid which
//    represents a map of '1's (land) and '0's (water), return the number of islands.
//
//    An island is surrounded by water and is formed by
//    connecting adjacent lands horizontally or vertically.
//    You may assume all four edges of the grid are all surrounded by water.
//
//
//    Example 1:
//    Input: grid = [
//            ["1","1","1","1","0"],
//            ["1","1","0","1","0"],
//            ["1","1","0","0","0"],
//            ["0","0","0","0","0"]
//            ]
//    Output: 1
//
//    Example 2:
//    Input: grid = [
//            ["1","1","0","0","0"],
//            ["1","1","0","0","0"],
//            ["0","0","1","0","0"],
//            ["0","0","0","1","1"]
//            ]
//    Output: 3
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 300
//    grid[i][j] is '0' or '1'.

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    visited[i][j] = true;
                    dfs(grid, visited, new Pair(i, j));
                    ans++;
                }
            }
        }

        return ans;
    }

    public void dfs(char[][] grid, boolean[][] visited, Pair node) {
        int m = grid.length;
        int n = grid[0].length;
        Stack<Pair> stack = new Stack<>();
        stack.push(node);

        int[] deltaRow = { -1, 0, 1, 0};
        int[] deltaCol = { 0, 1, 0, -1};

        while (!stack.isEmpty()) {
            Pair currNode = stack.pop();
            int row = currNode.row;
            int col = currNode.col;

            for (int i = 0; i < deltaRow.length; i++) {
                int nRow = row + deltaRow[i];
                int nCol = col + deltaCol[i];

                if (nRow >= 0 && nCol >= 0 && nRow < m && nCol < n && !visited[nRow][nCol] && grid[nRow][nCol] == '1') {
                    stack.push(new Pair(nRow, nCol));
                    visited[nRow][nCol] = true;
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


    // Recursive Way
    // Both are good, but this is a little faster
    int m;
    int n;
    public int numIslands2(char[][] grid) {
        int ans = 0;
        m = grid.length;
        n = grid[0].length;

        // traverse to matrix
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    dfs2(grid, i, j);
                    ans++;
                }
            }
        }
        return ans;

    }


    void dfs2(char[][] grid, int i, int j){
        if(i<0 || j<0 || i>=m || j>=n || grid[i][j] != '1'){
            return;
        }
        grid[i][j] = '0';
        dfs2(grid,i+1,j);
        dfs2(grid,i-1,j);
        dfs2(grid,i,j+1);
        dfs2(grid,i,j-1);

    }
}
