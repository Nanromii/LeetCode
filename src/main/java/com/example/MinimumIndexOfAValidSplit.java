package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumIndexOfAValidSplit {
    public int minimumIndex(List<Integer> nums) {
        int n = nums.size();
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int[] dominant = new int[]{0, 0};
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > dominant[1]) {
                dominant[0] = entry.getKey();
                dominant[1] = entry.getValue();
            }
        }
        System.out.println(dominant[0] + " " + dominant[1]);
        int[] prefix = new int[n];
        prefix[0] = (nums.get(0) == dominant[0]) ? 1 : 0;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1];
            if (nums.get(i) == dominant[0]) {
                prefix[i]++;
            }
        }
        for (int i = 0; i < n; i++) {
            int length = i + 1;
            if (prefix[i] * 2 > length && (dominant[1] - prefix[i]) * 2 > n - length) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {2,1,3,1,1,1,7,1,2,1};
        List<Integer> input = Arrays.stream(nums).boxed().toList();
        MinimumIndexOfAValidSplit m = new MinimumIndexOfAValidSplit();
        m.minimumIndex(input);
    }
}
