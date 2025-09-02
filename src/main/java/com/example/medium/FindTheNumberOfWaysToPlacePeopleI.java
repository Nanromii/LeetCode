package com.example.medium;

public class FindTheNumberOfWaysToPlacePeopleI {
    public int numberOfPairs(int[][] points) {
        int result = 0;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int[] a = points[i], b = points[j];
                if (i == j || a[0] > b[0] || a[1] < b[1]) continue;
                boolean valid = true;
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue;
                    int[] c = points[k];
                    if (a[0] <= c[0] && c[0] <= b[0] &&
                            b[1] <= c[1] && c[1] <= a[1]) {
                        valid = false;
                        break;
                    }
                }
                if (valid) result++;
            }
        }
        return result;
    }
}
