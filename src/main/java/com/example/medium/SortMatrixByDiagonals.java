package com.example.medium;

import java.util.*;

public class SortMatrixByDiagonals {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> listForEachLevel = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                listForEachLevel
                        .computeIfAbsent(i - j, k -> new ArrayList<>())
                        .add(grid[i][j]);
            }
        }
        for (Map.Entry<Integer, List<Integer>> entry : listForEachLevel.entrySet()) {
            if (entry.getKey() >= 0) {
                entry.getValue().sort(Comparator.reverseOrder());
            } else {
                Collections.sort(entry.getValue());
            }
        }
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                int curIdx = idx.getOrDefault(key, 0);
                grid[i][j] = listForEachLevel.get(key).get(curIdx);
                idx.put(key, curIdx + 1);
            }
        }
        return grid;
    }
}
