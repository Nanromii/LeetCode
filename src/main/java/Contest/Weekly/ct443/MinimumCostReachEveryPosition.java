package Contest.Weekly.ct443;

import java.util.Arrays;

public class MinimumCostReachEveryPosition {
    public int[] minCosts(int[] cost) {
        int n = cost.length;
        int[] result = new int[n];
        int cur = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (cur > cost[i]) {
                cur = cost[i];
            }
            result[i] = cur;
        }
        return result;
    }
}
