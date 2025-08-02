package com.example.hard;

import java.util.*;

class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
        }
        Map<String, List<String>> adjMap = new HashMap<>();
        initMap(adjMap, wordList);
        Map<String, Integer> distance = new HashMap<>();
        Set<String> visited = new HashSet<>();
        for (String word : wordList) {
            distance.put(word, Integer.MAX_VALUE);
        }
        distance.put(beginWord, 0);
        Queue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.dis));
        minHeap.add(new Pair(beginWord, 0));
        while (!minHeap.isEmpty()) {
            Pair curPair = minHeap.poll();
            String curWord = curPair.word;
            int curDistance = curPair.dis;
            if (curWord.equals(endWord)) return curDistance + 1;
            visited.add(curWord);
            for (String neighbor : adjMap.get(curWord)) {
                if (visited.contains(neighbor)) continue;
                if (curDistance + 1 < distance.get(neighbor)) {
                    distance.put(neighbor, curDistance + 1);
                    minHeap.add(new Pair(neighbor, curDistance + 1));
                }
            }
        }
        return 0;
    }

    private void initMap(Map<String, List<String>> adjMap, List<String> wordList) {
        for (String word : wordList) {
            adjMap.put(word, new ArrayList<>());
        }
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 0; j < wordList.size(); j++) {
                if (i == j) continue;
                String word1 = wordList.get(i);
                String word2 = wordList.get(j);
                if (isAdjacent(word1, word2)) {
                    adjMap.get(word1).add(word2);
                    adjMap.get(word2).add(word1);
                }
            }
        }
    }

    private boolean isAdjacent(String word1, String word2) {
        int differs = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                differs++;
            }
            if (differs > 1) return false;
        }
        return differs != 0;
    }

    static class Pair {
        String word;
        int dis;
        public Pair(String word, int dis) {
            this.word = word;
            this.dis = dis;
        }
    }
}
