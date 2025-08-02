package vnoicup.summer2025;

import java.util.*;

public class NumberTransformation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            long n = sc.nextInt(), m = sc.nextInt();
            if (n == m) {
                System.out.println(0);
                continue;
            }
            if (n > m || m % n != 0) {
                System.out.println(-1);
                continue;
            }
            Map<Long, List<Long>> path = new HashMap<>();
            Queue<Long> q = new LinkedList<>();
            Set<Long> visited = new HashSet<>();
            q.add(n);
            visited.add(n);
            path.put(n, new ArrayList<>());
            boolean found = false;
            while (!q.isEmpty()) {
                Long cur = q.poll();
                List<Long> currentPath = path.get(cur);
                for (Long d : getDivisors(cur)) {
                    if (d == 1) continue;
                    long next = cur * d;
                    if (next > m || m % next != 0 || visited.contains(next)) continue;
                    List<Long> newPath = new ArrayList<>(currentPath);
                    newPath.add(d);
                    path.put(next, newPath);
                    q.add(next);
                    visited.add(next);
                    if (next == m) {
                        System.out.print(newPath.size());
                        for (Long x : newPath) System.out.print(" " + x);
                        System.out.println();
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            if (!found) {
                System.out.println(-1);
            }
        }
    }

    static List<Long> getDivisors(long n) {
        List<Long> res = new ArrayList<>();
        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                res.add(i);
                if (i != n / i) res.add(n / i);
            }
        }
        return res;
    }
}
