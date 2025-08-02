package Contest.Weekly.ct441;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaximumUniqueSubarraySumAfterDeletion {
    public int maxSum(int[] nums) {
        Set<Integer> containNum = new HashSet<>();
        if (nums.length == 1) return nums[0];
        boolean checkPositive = false;
        for (int num : nums) {
            containNum.add(num);
            if (num >= 0) checkPositive = true;
        }
        int result = 101;
        for (int num : containNum) {
            if (checkPositive) {
                if (num >= 0) result += num;
            } else {
                result = Math.min(result, num);
            }
        }
        return checkPositive ? result - 101 : result;
    }
}
