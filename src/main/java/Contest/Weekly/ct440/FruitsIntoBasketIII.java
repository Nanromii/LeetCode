package Contest.Weekly.ct440;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

public class FruitsIntoBasketIII {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        TreeMap<Integer, Deque<Integer>> basketMap = new TreeMap<>();
        for (int i = 0; i < baskets.length; i++) {
            basketMap.putIfAbsent(baskets[i], new ArrayDeque<>());
            basketMap.get(baskets[i]).offer(i);
        }
        int unplaced = 0;
        for (int fruit : fruits) {
            Integer key = basketMap.ceilingKey(fruit);
            if (key == null) {
                unplaced++;
            } else {
                Deque<Integer> indices = basketMap.get(key);
                indices.poll();
                if (indices.isEmpty()) {
                    basketMap.remove(key);
                }
            }
        }

        return unplaced;
    }
}
