package com.example;

public class MinimumTimeToRepairCar {
    public long repairCars(int[] ranks, int cars) {
        long left = 0, right = 100 * (long) cars * cars;
        long result = -1;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (isValid(mid, ranks, cars)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    private boolean isValid(long time, int[] ranks, int totalCars) {
        long carsFixed = 0;
        for (int rank : ranks) {
            carsFixed += (long) Math.sqrt(time / rank);
            if (carsFixed >= totalCars) return true;
        }
        return carsFixed >= totalCars;
    }
}
