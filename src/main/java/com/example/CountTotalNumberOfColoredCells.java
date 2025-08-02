package com.example;

public class CountTotalNumberOfColoredCells {
    public static int coloredCells(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result += 4 * (i - 1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(coloredCells(3));
    }
}
