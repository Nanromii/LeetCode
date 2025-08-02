package com.example;

import java.util.*;

public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] prev = new int[n];
        Arrays.sort(nums);
        Arrays.fill(prev, -1);
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > dp[idx]) idx = i;
        }
        List<Integer> result = new ArrayList<>();
        while (idx >= 0) {
            result.add(nums[idx]);
            idx = prev[idx];
        }
        return result;
    }
}
