package Contest.Weekly.ct440;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.TreeMap;

public class FruitsIntoBasketII {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        TreeMap<Integer, Integer> availableBaskets = new TreeMap<>();
        int unplaced = 0, n = fruits.length, basketIndex = 0;
        for (int fruit : fruits) {
            while (basketIndex < n) {
                availableBaskets.put(baskets[basketIndex], availableBaskets.getOrDefault(baskets[basketIndex], 0) + 1);
                basketIndex++;
            }
            Integer validBasket = availableBaskets.ceilingKey(fruit);
            if (validBasket == null) {
                unplaced++;
            } else {
                if (availableBaskets.get(validBasket) == 1) {
                    availableBaskets.remove(validBasket);
                } else {
                    availableBaskets.put(validBasket, availableBaskets.get(validBasket) - 1);
                }
            }
        }
        return unplaced;
    }
}
