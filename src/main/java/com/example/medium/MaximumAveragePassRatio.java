package com.example.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumAveragePassRatio {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        Queue<int[]> maxHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                double e1 = ((double) (o1[0] + 1) / (o1[1] + 1)) - ((double) o1[0] / o1[1]);
                double e2 = ((double) (o2[0] + 1) / (o2[1] + 1)) - ((double) o2[0] / o2[1]);
                return Double.compare(e2, e1);
            }
        });
        maxHeap.addAll(Arrays.asList(classes));
        while (extraStudents-- > 0 && !maxHeap.isEmpty()) {
            int[] cl = maxHeap.poll();
            cl[0] += 1; cl[1] += 1;
            maxHeap.add(cl);
        }
        double averagePassRatio = 0.0;
        while (!maxHeap.isEmpty()) {
            int[] cl = maxHeap.poll();
            averagePassRatio += (double) cl[0] / cl[1];
        }
        return averagePassRatio / classes.length;
    }
}
