package com.example.hard;

import java.util.*;

public class RearrangeFruits {
    public static void main(String[] args) {
        int[] b1 = {84,80,43,8,80,88,43,14,100,88};
        int[] b2 = {32,32,42,68,68,100,42,84,14,8};
        System.out.println(minCost(b1, b2));
    }

    public static long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> freq1 = new HashMap<>();
        Map<Integer, Integer> freq2 = new HashMap<>();
        Set<Integer> basket = new HashSet<>();
        int min = Integer.MAX_VALUE;
        for (int fruit : basket1) {
            freq1.put(fruit, freq1.getOrDefault(fruit, 0) + 1);
            basket.add(fruit);
            min = Math.min(min, fruit);
        }
        for (int fruit : basket2) {
            freq2.put(fruit, freq2.getOrDefault(fruit, 0) + 1);
            basket.add(fruit);
            min = Math.min(min, fruit);
        }

        List<Integer> fruitNeedRearrange1 = new ArrayList<>();
        List<Integer> fruitNeedRearrange2 = new ArrayList<>();
        for (int fruit : basket) {
            int count1 = freq1.getOrDefault(fruit, 0);
            int count2 = freq2.getOrDefault(fruit, 0);
            if ((count2 + count1) % 2 != 0) return -1;
            if (count1 > count2) {
                for (int i = 0; i < Math.abs(count1 - count2) / 2; i++) {
                    fruitNeedRearrange1.add(fruit);
                }
            } else if (count2 > count1) {
                for (int i = 0; i < Math.abs(count1 - count2) / 2; i++) {
                    fruitNeedRearrange2.add(fruit);
                }
            }
        }
        /*for (int num : fruitNeedRearrange1) {
            System.out.println(num);
        }
        System.out.println("----------");
        for (int num : fruitNeedRearrange2) {
            System.out.println(num);
        }*/
        if (fruitNeedRearrange1.size() != fruitNeedRearrange2.size()) return -1;
        Collections.sort(fruitNeedRearrange1);
        fruitNeedRearrange2.sort(Collections.reverseOrder());
        long cost = 0;
        for (int i = 0; i < fruitNeedRearrange1.size(); i++) {
            int directSwapCost = Math.min(fruitNeedRearrange1.get(i), fruitNeedRearrange2.get(i));
            cost += Math.min(directSwapCost, 2 * min);
        }
        return cost;
    }
}
