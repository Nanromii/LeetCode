package com.example.medium;

import java.util.*;

public class ReorderedPowerOf2 {
    public boolean reorderedPowerOf2(int n) {
        int[] digits = countDigits(n);
        for (int i = 0; i < 31; i++) {
            int pow = 1 << i;
            int[] freqPow = countDigits(pow);
            boolean isValid = true;
            for (int j = 0; j < 10; j++) {
                if (digits[j] != freqPow[j]) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) return true;
        }
        return false;
    }

    private int[] countDigits(int n) {
        int[] freq = new int[10];
        while (n != 0) {
            freq[n % 10] += 1;
            n /= 10;
        }
        return freq;
    }
}