package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class FindAllPossibleRecipesFromGivenSupplies {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> adjMap = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        for (int i = 0; i < recipes.length; i++) {
            inDegree.put(recipes[i], ingredients.get(i).size());
            for (String ing : ingredients.get(i)) {
                adjMap.computeIfAbsent(ing, k -> new ArrayList<>()).add(recipes[i]);
            }
        }
        Queue<String> q = new LinkedList<>(Arrays.stream(supplies).collect(Collectors.toSet()).stream().toList());
        List<String> result = new LinkedList<>();
        while (!q.isEmpty()) {
            String supply = q.poll();
            if (inDegree.containsKey(supply) && inDegree.get(supply) == 0) {
                inDegree.remove(supply);
                result.add(supply);
            }
            for (String recipe : adjMap.getOrDefault(supply, new LinkedList<>())) {
                inDegree.put(recipe, inDegree.get(recipe) - 1);
                if (inDegree.get(recipe) == 0) {
                    q.add(recipe);
                }
            }
        }
        return result;
    }
}
