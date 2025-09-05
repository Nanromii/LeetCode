package com.example.medium;

public class MinimumOperationsToMakeTheIntegerZero {
    public static void main(String[] args) {
        System.out.println(Integer.bitCount(3));
    }

    public int makeTheIntegerZero(int num1, int num2) {
        for (int k = 1; k <= 60; k++) {
            long x = (long) num1 - (long) k * num2;
            if (x < 0) continue;
            if (Long.bitCount(x) <= k && k <= x) {
                return k;
            }
        }
        return -1;
    }
}
