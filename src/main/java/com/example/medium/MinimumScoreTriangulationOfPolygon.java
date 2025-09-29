package com.example.medium;

import java.util.Arrays;

public class MinimumScoreTriangulationOfPolygon {
    public int minScoreTriangulation(int[] values) {
        int[][] dp = new int[values.length][values.length];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return helper(0, values.length - 1, values, dp);
    }

    private int helper(int i, int j, int[] values, int[][] dp) {
        if (j - i < 2) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        int result = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            result = Math.min(result,
                    values[i] * values[j] * values[k]
                            + helper(i, k, values, dp)
                            + helper(k, j, values, dp));
        }
        return dp[i][j] = result;
    }
}