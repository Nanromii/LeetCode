package Contest.Weekly.ct441;

import java.util.*;

public class ClosestEqualElementQueries {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        for (List<Integer> list : map.values()) {
            if (list.size() < 2) continue;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                int curr = list.get(i);
                int d1 = (i == 0 ? curr + n - list.get(size - 1) : curr - list.get(i - 1));
                int d2 = (i == size - 1 ? list.get(0) + n - curr : list.get(i + 1) - curr);
                ans[curr] = Math.min(d1, d2);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int q : queries) {
            res.add(ans[q]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {15,1,10,1,20,4,6,14,4,9,4,18};
        int[] queries = {0,2,10,6,11,8};
        ClosestEqualElementQueries s = new ClosestEqualElementQueries();
        s.solveQueries(nums, queries).forEach(System.out::println);
    }
}
