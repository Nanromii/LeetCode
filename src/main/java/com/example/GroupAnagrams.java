package com.example;

import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            map.computeIfAbsent(new String(temp), k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
