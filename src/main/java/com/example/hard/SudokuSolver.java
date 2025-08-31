package com.example.hard;

public class SudokuSolver {
    private boolean[][] row;
    private boolean[][] col;
    private boolean[][][] matrix;

    public void solveSudoku(char[][] board) {
        row = new boolean[board.length][10];
        col = new boolean[board[0].length][10];
        matrix = new boolean[3][3][10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') continue;
                row[i][board[i][j] - '0'] = true;
                col[j][board[i][j] - '0'] = true;
                matrix[i / 3][j / 3][board[i][j] - '0'] = true;
            }
        }
        solve(0, 0, board);
    }

    private boolean solve(int i, int j, char[][] board) {
        if (i >= board.length) return true;
        if (j >= board[0].length) {
            return solve(i + 1, 0, board);
        }
        if (board[i][j] != '.') {
            return solve(i, j + 1, board);
        }
        for (char num = '1'; num <= '9'; num++) {
            if (!row[i][num - '0'] && !col[j][num - '0'] && !matrix[i / 3][j / 3][num - '0']) {
                board[i][j] = num;
                row[i][board[i][j] - '0'] = true;
                col[j][board[i][j] - '0'] = true;
                matrix[i / 3][j / 3][board[i][j] - '0'] = true;
                if (solve(i, j + 1, board)) {
                    return true;
                }
                board[i][j] = '.';
                row[i][board[i][j] - '0'] = false;
                col[j][board[i][j] - '0'] = false;
                matrix[i / 3][j / 3][board[i][j] - '0'] = false;
            }
        }
        return false;
    }
}
