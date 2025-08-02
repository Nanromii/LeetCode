package com.example;

import java.util.*;

public class MaximumNumberOfPointsFromGridQueries {
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int[] maxPoints(int[][] grid, int[] queries) {
        int[][] sortedQueries = sortQueries(queries);
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<int[]> pq = init(grid, visited);
        return getResult(sortedQueries, grid, visited, pq);
    }

    private int[][] sortQueries(int[] queries) {
        int n = queries.length;
        int[][] sortedQueries = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[0]));
        return sortedQueries;
    }
    private PriorityQueue<int[]> init(int[][] grid, boolean[][] visited) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;
        return pq;
    }

    private int[] getResult(int[][] sortedQueries, int[][] grid, boolean[][] visited, PriorityQueue<int[]> pq) {
        int[] result = new int[sortedQueries.length];
        int points = 0;
        for (int[] q : sortedQueries) {
            int query = q[0], idx = q[1];
            points = dfs(query, grid, visited, pq, points);
            result[idx] = points;
        }
        return result;
    }

    private int dfs(int query, int[][] grid, boolean[][] visited, PriorityQueue<int[]> pq, int points) {
        int rows = grid.length, cols = grid[0].length;
        while (!pq.isEmpty() && pq.peek()[0] < query) {
            int[] cell = pq.poll();
            int r = cell[1], c = cell[2];
            points++;
            for (int[] dir : DIRECTIONS) {
                int newR = r + dir[0], newC = c + dir[1];
                if (newR >= 0 && newR < rows && newC >= 0 && newC < cols && !visited[newR][newC]) {
                    visited[newR][newC] = true;
                    pq.offer(new int[]{grid[newR][newC], newR, newC});
                }
            }
        }
        return points;
    }
}
