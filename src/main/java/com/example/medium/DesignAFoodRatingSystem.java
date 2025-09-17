package com.example.medium;

import java.util.*;

public class DesignAFoodRatingSystem {
    class FoodRatings {
        private Map<String, String> cuisineOfFood;
        private Map<String, Food> latestFood;
        private Map<String, PriorityQueue<Food>> listSortedFood;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            cuisineOfFood = new HashMap<>();
            latestFood = new HashMap<>();
            listSortedFood = new HashMap<>();
            for (int i = 0; i < foods.length; i++) {
                Food food = new Food(foods[i], ratings[i]);
                listSortedFood
                        .computeIfAbsent(cuisines[i], k -> new PriorityQueue<>(
                                Comparator.<Food>comparingInt(f -> f.rating).reversed()
                                        .thenComparing(f -> f.name)
                        ))
                        .add(food);
                cuisineOfFood.put(foods[i], cuisines[i]);
                latestFood.put(foods[i], food);
            }
        }

        public void changeRating(String foodName, int newRating) {
            String cuisine = cuisineOfFood.get(foodName);
            Food updated = new Food(foodName, newRating);
            listSortedFood.get(cuisine).add(updated);
            latestFood.put(foodName, updated);
        }

        public String highestRated(String cuisine) {
            PriorityQueue<Food> pq = listSortedFood.get(cuisine);
            while (true) {
                Food top = pq.peek();
                Food latest = latestFood.get(top.name);
                if (top == latest) {
                    return top.name;
                }
                pq.poll();
            }
        }

        class Food {
            String name;
            int rating;

            public Food(String name, int rating) {
                this.name = name;
                this.rating = rating;
            }
        }
    }
}
