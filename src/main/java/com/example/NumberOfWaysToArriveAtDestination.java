package com.example;

import java.util.*;

public class NumberOfWaysToArriveAtDestination {
    private final int MOD = (int) (1e9 + 7);

    public int countPaths(int n, int[][] roads) {
        Map<Integer, List<int[]>> adjMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjMap.put(i, new ArrayList<>());
        }
        for (int[] road : roads) {
            adjMap.get(road[0]).add(new int[]{road[1], road[2]});
            adjMap.get(road[1]).add(new int[]{road[0], road[2]});
        }
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n];
        int[] ways = new int[n];
        dijkstra(adjMap, visited, distance, ways);
        return ways[n - 1];
    }

    private void dijkstra(Map<Integer, List<int[]>> adjMap, boolean[] visited, int[] distance, int[] ways) {
        ways[0] = 1;
        distance[0] = 0;
        visited[0] = true;
        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        minHeap.add(new int[]{0, 0});
        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int node = current[0], dis = current[1];
            for (int[] neighbor : adjMap.get(node)) {
                int neighborNode = neighbor[0], neighborDis = neighbor[1];
                if (neighborDis + dis < distance[neighborNode]) {
                    distance[neighborNode] = neighborDis + dis;
                    minHeap.add(new int[]{neighborNode, distance[neighborNode]});
                    visited[neighborNode] = true;
                } else if (neighborDis + dis == distance[neighborNode]) {
                    ways[neighborNode] += ways[node] % MOD;
                }
            }
        }
    }

    public static void main(String[] args) {
        NumberOfWaysToArriveAtDestination m = new NumberOfWaysToArriveAtDestination();
        int n = 7;
        int[][] roads = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
        m.countPaths(n, roads);
    }
}
