package Contest.Biweekly.ct153;

import java.util.ArrayList;
import java.util.List;

public class MaximizeActiveSectionWithTradeII {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int[] prefix = new int[n];
        prefix[0] = s.charAt(0) == '1' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + (s.charAt(0) == '1' ? 1 : 0);
        }
        return new ArrayList<>();
    }
}
