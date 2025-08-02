package Contest.Biweekly.ct153;

import java.util.ArrayList;
import java.util.List;

public class MaximizeActiveSectionWithTradeI {
    public int maxActiveSectionsAfterTrade(String s) {
        if (s.length() == 1) return s.charAt(0) == '1' ? 1 : 0;
        int n = s.length(), countActive = 0;
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < s.length();) {
            char cur = s.charAt(i);
            int count = 0;
            while (i < n && s.charAt(i) == cur) {
                count++;
                if (s.charAt(i) == '1') countActive++;
                i++;
            }
            pairs.add(new Pair(cur, count));
        }
        if (pairs.size() == 1) {
            if (pairs.get(0).isActive == '1') return pairs.get(0).freq;
            return 0;
        }
        int result = countActive;
        for (int j = 0; j < pairs.size(); j++) {
            if (pairs.get(j).isActive == '1' && j != 0 && j != pairs.size() - 1) {
                int num = j < pairs.size() - 1 ? pairs.get(j + 1).freq : 0;
                result = Math.max(
                        result,
                        countActive + num + pairs.get(j - 1).freq);
            }
        }
        return result;
    }

    class Pair {
        char isActive;
        int freq;
        public Pair(char isActive, int freq) {
            this.isActive = isActive;
            this.freq = freq;
        }
    }

    public static void main(String[] args) {
        MaximizeActiveSectionWithTradeI m = new MaximizeActiveSectionWithTradeI();
        System.out.println(m.maxActiveSectionsAfterTrade("01010"));
    }
}
