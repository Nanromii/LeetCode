package com.example;

import java.util.Arrays;

public class PutMarblesInBag {
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        int[] cut = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            cut[i] = weights[i] + weights[i + 1];
        }
        Arrays.sort(cut);
        long min = 0, max = 0;
        for (int i = 0; i < n - 1; i++) {
            if (k-- > 1) {
                min += cut[i];
                //System.out.println(cut[i]);
                max += cut[n - 2 - i];
                //System.out.println(cut[n - 2 - i]);
                //System.out.println("------------");
            }
        }
        //System.out.println(min + " " + max);
        return max - min;
    }
}
