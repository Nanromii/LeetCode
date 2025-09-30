package com.example.hard;

import java.util.*;

public class FindTheNumberOfWaysToPlacePeopleII {
    class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            }
            Point p = (Point) o;
            return p.x == this.x && p.y == this.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }
    }

    public int numberOfPairs(int[][] points) {
        Set<Point> set = new HashSet<>();
        for (int[] p : points) {
            set.add(new Point(p[0], p[1]));
        }
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        for (Point p : set) {
            xs.add(p.x);
            ys.add(p.y);
        }
        List<Integer> sortedX = new ArrayList<>(new HashSet<>(xs));
        List<Integer> sortedY = new ArrayList<>(new HashSet<>(ys));
        Collections.sort(sortedX);
        Collections.sort(sortedY);

        Map<Integer, Integer> mapX = new HashMap<>();
        Map<Integer, Integer> mapY = new HashMap<>();
        for (int i = 0; i < sortedX.size(); i++) {
            mapX.put(sortedX.get(i), i + 1);
        }
        for (int i = 0; i < sortedY.size(); i++) {
            mapY.put(sortedY.get(i), i + 1);
        }
        int nx = sortedX.size();
        int ny = sortedY.size();
        int[][] pointSet = new int[nx + 1][ny + 1];
        Map<Point, int[]> coordMap = new HashMap<>();
        for (Point p : set) {
            int cx = mapX.get(p.x);
            int cy = mapY.get(p.y);
            pointSet[cx][cy] = 1;
            coordMap.put(p, new int[]{cx, cy});
        }
        int[][] prefixSum = new int[nx + 1][ny + 1];
        for (int i = 1; i <= nx; i++) {
            for (int j = 1; j <= ny; j++) {
                prefixSum[i][j] =
                          prefixSum[i - 1][j]
                        + prefixSum[i][j - 1]
                        - prefixSum[i - 1][j - 1]
                        + pointSet[i][j];
            }
        }
        List<Point> pointList = new ArrayList<>(set);
        int result = 0;
        int m = pointList.size();
        pointList.sort((a, b) -> {
            if (a.x == b.x) return Integer.compare(b.y, a.y);
            return Integer.compare(a.x, b.x);
        });
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                Point p1 = pointList.get(i);
                Point p2 = pointList.get(j);
                if (p1.y >= p2.y) {
                    int[] coord1 = coordMap.get(p1);
                    int[] coord2 = coordMap.get(p2);
                    int c1 = coord1[0], r1 = coord1[1];
                    int c2 = coord2[0], r2 = coord2[1];
                    int cnt = prefixSum[c2][r1]
                            - prefixSum[c1 - 1][r1]
                            - prefixSum[c2][r2 - 1]
                            + prefixSum[c1 - 1][r2 - 1];
                    if (cnt == 2) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
}
