package com.example.medium;

public class NumberOfPeopleAwareOfSecret {
    public static void main(String[] args) {
        System.out.println(peopleAwareOfSecret(6, 2, 4));
    }

    public static int peopleAwareOfSecret(int n, int delay, int forget) {
        final int MOD = (int) 1e9 + 7;
        long[] dp = new long[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = Math.max(1, i - forget + 1); j <= i - delay; j++) {
                dp[i] = (dp[i] + dp[j]) % MOD;
            }
        }
        long ans = 0;
        for (int i = n - forget + 1; i <= n; i++) {
            ans = (ans + dp[i]) % MOD;
        }
        return (int) ans;
    }
}
