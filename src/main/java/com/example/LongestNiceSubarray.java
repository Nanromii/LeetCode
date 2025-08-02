package com.example;

public class LongestNiceSubarray {
    private int getBit(int num, int position) {
        return (num >> position) & 1;
    }

    private boolean compareBitPosition(int[] bits, int num) {
        for (int i = 0; i < 32; i++) {
            int bitOfNum = getBit(num, i);
            if ((bits[i] & bitOfNum) == 1) {
                return false;
            }
        }
        for (int i = 0; i < 32; i++) {
            bits[i] |= getBit(num, i);
        }
        return true;
    }

    public int longestNiceSubarray(int[] nums) {
        int n = nums.length, left = 0, right = 0, result = 1;
        int[] bits = new int[32];
        while (right < n) {
            while (right < n && compareBitPosition(bits, nums[right])) {
                right++;
                result = Math.max(right - left, result);
            }
            for (int i = 0; i < 32; i++) {
                bits[i] &= ~getBit(nums[left], i);
            }
            left++;
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println((1 >> 31) & 1);
    }
}
