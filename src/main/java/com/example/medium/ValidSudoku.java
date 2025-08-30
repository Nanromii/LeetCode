package com.example.medium;

public class ValidSudoku {
    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        isValidSudoku(board);
    }

    public static boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][][] matrix = new boolean[3][3][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                if (
                        row[i][board[i][j] - '0']
                                || col[j][board[i][j] - '0']
                                || matrix[i / 3][j / 3][board[i][j] - '0']
                ) return false;
                row[i][board[i][j] - '0'] = true;
                col[j][board[i][j] - '0'] = true;
                matrix[i / 3][j / 3][board[i][j] - '0'] = true;
            }
        }
        return true;
    }
}
