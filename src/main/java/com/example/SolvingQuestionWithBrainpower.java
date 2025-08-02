package com.example;

public class SolvingQuestionWithBrainpower {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            int[] q = questions[i];
            int nextIndex = i + q[1] + 1;
            long take = (nextIndex < n ? dp[nextIndex] : 0) + q[0];
            long skip = (i + 1 < n ? dp[i + 1] : 0);
            dp[i] = Math.max(take, skip);
        }
        return dp[0];
    }
}
