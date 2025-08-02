package Contest.Biweekly.ct152;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSpecialPathII {
    private int maxLength = Integer.MIN_VALUE;
    private int minNodes = Integer.MAX_VALUE;
    public int[] longestSpecialPath(int[][] edges, int[] nums) {
        int n = edges.length + 1;
        Map<Integer, List<Pair>> adjMap = new HashMap<>();
        for (int[] edge : edges) {
            adjMap.computeIfAbsent(edge[0], k -> new ArrayList<>());
            adjMap.computeIfAbsent(edge[1], k -> new ArrayList<>());
            adjMap.get(edge[0]).add(new Pair(edge[1], edge[2]));
            adjMap.get(edge[1]).add(new Pair(edge[0], edge[2]));
        }
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            dfs(adjMap, freq, n, new boolean[n], i);
        }
        return new int[]{maxLength, minNodes};
    }

    private void dfs(Map<Integer, List<Pair>> adjMap, Map<Integer, Integer> freq, int n, boolean[] visited, int vertex) {
        if (visited[vertex]) return;

    }

    class Pair {
        int v, w;
        public Pair(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
