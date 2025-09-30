package com.example.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindTriangularSumOfAnArray {
    public int triangularSum(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().toList();
        while (list.size() > 1) {
            List<Integer> newList = new ArrayList<>();
            for (int i = 0; i < list.size() - 1; i++) {
                int val = (list.get(i) + list.get(i + 1)) % 10;
                newList.add(val);
            }
            list = newList;
        }
        return list.get(0);
    }
}
