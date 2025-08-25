package com.example.medium;

import java.util.Arrays;

public class DiagonalTraverse {
    public static void main(String[] args) {
        int[][] a = {{2, 3}};
        System.out.println(Arrays.stream(findDiagonalOrder(a)));
    }

    public static int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length, m = mat[0].length, idx = 0;
        int[] result = new int[n * m];
        for (int level = 0; level < 2 * Math.max(n, m) - 1; level++) {
            if (level % 2 == 0) {
                for (int i = Math.min(n - 1, level); i >= 0; i--) {
                    for (int j = level - i; j <= Math.min(m - 1, level) && j + i <= level; j++) {
                        result[idx++] = mat[i][j];
                    }
                }
            } else {
                for (int j = Math.min(m - 1, level); j >= 0; j--) {
                    for (int i = level - j; i <= Math.min(n - 1, level) && j + i <= level; i++) {
                        result[idx++] = mat[i][j];
                    }
                }
            }
        }
        return result;
    }
}
