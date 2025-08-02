package Contest.Weekly.ct442;

import java.util.*;

public class PropertiesGraph {
    public int numberOfComponents(int[][] properties, int k) {
        if (properties.length == 1) return 1;
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int i = 0; i < properties.length - 1; i++) {
            for (int j = i + 1; j < properties.length; j++) {
                adjMap.putIfAbsent(i, new ArrayList<>());
                adjMap.putIfAbsent(j, new ArrayList<>());
                if (intersect(properties[i], properties[j], k)) {
                    adjMap.get(i).add(j);
                    adjMap.get(j).add(i);
                }
            }
        }
        return getNumberComponent(adjMap);
    }

    private boolean intersect(int[] a, int[] b, int k) {
        Set<Integer> setA = new HashSet<>();
        for (int num : a) {
            setA.add(num);
        }
        int count = 0;
        Set<Integer> setB = new HashSet<>();
        for (int num : b) {
            if (setA.contains(num) && !setB.contains(num)) {
                count++;
                setB.add(num);
            }
        }
        return count >= k;
    }

    private int getNumberComponent(Map<Integer, List<Integer>> adjMap) {
        int size = adjMap.size();
        int components = 0;
        boolean[] visited = new boolean[size];
        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                dfs(adjMap, i, visited);
                components++;
            }
        }
        return components;
    }

    private void dfs(Map<Integer, List<Integer>> adjMap, int u, boolean[] visited) {
        visited[u] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(u);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int nei : adjMap.get(v)) {
                if (!visited[nei]) {
                    q.add(nei);
                    visited[nei] = true;
                }
            }
        }
    }
}
