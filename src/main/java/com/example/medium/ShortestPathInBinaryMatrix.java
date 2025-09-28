package com.example.medium;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
    private final int[][] DIRECTIONS = {
            {-1, 0}, {0, -1}, {1, 0}, {0, 1},
            {1, 1}, {-1, -1}, {-1, 1}, {1, -1}
    };

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1) return -1;
        Queue<Unit> minHeap = new LinkedList<>();
        minHeap.add(new Unit(0, 0 , 1));
        boolean[][] visited = new boolean[n][m];
        while (!minHeap.isEmpty()) {
            Unit unit = minHeap.poll();
            if (unit.i == n - 1 && unit.j == m - 1) return unit.dis;
            if (visited[unit.i][unit.j]) continue;
            visited[unit.i][unit.j] = true;
            for (int[] direction : DIRECTIONS) {
                int ni = unit.i + direction[0];
                int nj = unit.j + direction[1];
                if (isValid(ni, nj, n, m)
                        && grid[ni][nj] == 0
                        && !visited[ni][nj]) {
                    minHeap.add(new Unit(ni, nj, unit.dis + 1));
                }
            }
        }
        return -1;
    }

    private boolean isValid(int i, int j, int n, int m) {
        return 0 <= i && i < n && 0 <= j && j < m;
    }

    class Unit {
        int i, j, dis;

        public Unit(int i, int j, int dis) {
            this.i = i;
            this.j = j;
            this.dis = dis;
        }
    }
}
