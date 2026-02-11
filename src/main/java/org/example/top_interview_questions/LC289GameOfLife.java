package org.example.top_interview_questions;

public class LC289GameOfLife {
//    According to Wikipedia's article: "The Game of Life, also known simply as Life,
//    is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
//
//    The board is made up of an m x n grid of cells, where each cell has an initial state:
//    live (represented by a 1) or dead (represented by a 0).
//    Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
//    using the following four rules (taken from the above Wikipedia article):
//
//    Any live cell with fewer than two live neighbors dies as if caused by under-population.
//    Any live cell with two or three live neighbors lives on to the next generation.
//    Any live cell with more than three live neighbors dies, as if by over-population.
//    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
//    The next state of the board is determined by applying the above rules simultaneously to
//    every cell in the current state of the m x n grid board. In this process, births and deaths occur simultaneously.
//
//    Given the current state of the board, update the board to reflect its next state.
//
//    Note that you do not need to return anything.
//
//
//
//    Example 1:
//    Input:  board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
//
//    Initial State:          Next State:
//    +---+---+---+           +---+---+---+
//    | 0 | 1 | 0 |           | 0 | 0 | 0 |
//    +---+---+---+           +---+---+---+
//    | 0 | 0 | 1 |           | 1 | 0 | 1 |
//    +---+---+---+           +---+---+---+
//    | 1 | 1 | 1 |           | 0 | 1 | 1 |
//    +---+---+---+           +---+---+---+
//    | 0 | 0 | 0 |           | 0 | 1 | 0 |
//    +---+---+---+           +---+---+---+
//
//    Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
//
//
//
//    Example 2:
//    Input:  board = [[1,1],[1,0]]
//
//    Initial State:      Next State:
//    +---+---+           +---+---+
//    | 1 | 1 |           | 1 | 1 |
//    +---+---+           +---+---+
//    | 1 | 0 |           | 1 | 1 |
//    +---+---+           +---+---+
//
//    Output: [[1,1],[1,1]]
//
//
//
//    Constraints:
//
//    m == board.length
//    n == board[i].length
//    1 <= m, n <= 25
//    board[i][j] is 0 or 1.
//
//
//    Follow up:
//
//    Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
//    In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?



    // for transition 0->1 store 2.
    // for transition 1->0 store -1.
    // We will treat 2 as 0 and -1 as 1 when calculating live neighbors of a cell(liveNeighbors)
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = neighbor(board, i-1, j) + neighbor(board, i+1, j)  // vertical neighbour
                        + neighbor(board, i, j-1) + neighbor(board, i, j+1)  // horizontal neighbor
                        + neighbor(board, i-1, j-1) + neighbor(board, i+1, j+1) // diagonal neighbor
                        + neighbor(board, i-1, j+1) + neighbor(board, i+1, j-1); // diagonal neighbor

                if (board[i][j] == 1) {
                    board[i][j] = (liveNeighbors == 2 || liveNeighbors == 3) ? 1 : -1;
                } else {
                    board[i][j] = (liveNeighbors == 3) ? 2 : 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] >= 1 ? 1 : 0;
            }
        }
    }

    public int neighbor(int[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 0 || board[i][j] == 2) {
            // we are return board[i][j] == 0 || board[i][j] == 2 because
            // we are replacing 0 to 2 when 0->1 (0 become 1)
            // treat 2 as 0
            return 0;
        }

        // for other cases 1 and -1(1->0), treat them as 1
        return 1;
    }

    // Simple and easy solution but uses O(n^2) Space
    public void gameOfLife2(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = neighbor(board, i-1, j) + neighbor(board, i+1, j)  // vertical neighbour
                        + neighbor(board, i, j-1) + neighbor(board, i, j+1)  // horizontal neighbor
                        + neighbor(board, i-1, j-1) + neighbor(board, i+1, j+1) // diagonal neighbor
                        + neighbor(board, i-1, j+1) + neighbor(board, i+1, j-1); // diagonal neighbor

                if (board[i][j] == 1) {
                    res[i][j] = (liveNeighbors == 2 || liveNeighbors == 3) ? 1 : 0;
                } else {
                    res[i][j] = (liveNeighbors == 3) ? 1 : 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = res[i][j];
            }
        }
    }

    public int neighbor2(int[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 0) {
            return 0;
        }
        return 1;
    }

}
