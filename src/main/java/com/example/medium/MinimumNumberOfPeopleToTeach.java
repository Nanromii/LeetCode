package com.example.medium;

import java.util.*;

public class MinimumNumberOfPeopleToTeach {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        List<Set<Integer>> userLanguages = new ArrayList<>();
        for (int i = 0; i < languages.length; i++) {
            Set<Integer> term = new HashSet<>();
            for (int lang : languages[i]) {
                term.add(lang);
            }
            userLanguages.add(term);
        }
        Set<Integer> needTeach = new HashSet<>();
        for (int[] friendship : friendships) {
            int u = friendship[0], v = friendship[1];
            if (Collections.disjoint(userLanguages.get(u - 1), userLanguages.get(v - 1))) {
                needTeach.add(u - 1);
                needTeach.add(v - 1);
            }
        }
        int result = Integer.MAX_VALUE;
        for (int lang = 1; lang <= n; lang++) {
            int count = 0;
            for (int user : needTeach) {
                if (!userLanguages.get(user).contains(lang)) {
                    count++;
                }
            }
            result = Math.min(result, count);
        }
        return result;
    }
}
