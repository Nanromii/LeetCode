package com.example;

import java.util.Arrays;
import java.util.Comparator;

public class CheckIfGridCanBeCutIntoSections {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        int m = rectangles.length;
        int[][] Ox = new int[m][2], Oy = new int[m][2];
        for (int i = 0; i < m; i++) {
            int[] rectangle = rectangles[i];
            Ox[i] = new int[]{rectangle[0], rectangle[2]};
            Oy[i] = new int[]{rectangle[1], rectangle[3]};
        }
        return checkValidCutOfOneDirection(Ox) || checkValidCutOfOneDirection(Oy);
    }

    private boolean checkValidCutOfOneDirection(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
        int count = 1;
        int[] cur = pairs[0];
        for (int i = 1; i < pairs.length; i++) {
            int[] pair = pairs[i];
            if (cur[1] <= pair[0]) {
                count++;
                if (count >= 3) return true;
            }
            cur[1] = Math.max(cur[1], pair[1]);
        }
        return false;
    }
}
