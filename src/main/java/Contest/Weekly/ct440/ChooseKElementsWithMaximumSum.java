package Contest.Weekly.ct440;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ChooseKElementsWithMaximumSum {
    public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        Triple[] temp = new Triple[n];
        for (int i = 0; i < n; i++) {
            temp[i] = new Triple(nums1[i], nums2[i], i);
        }
        Arrays.sort(temp, Comparator.comparingInt(t -> t.value1));

        Queue<Integer> minHeap = new PriorityQueue<>();
        long[] prefixSum = new long[n];
        long sum = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            int curValue1 = temp[i].value1;
            while (j < i && temp[j].value1 < curValue1) {
                int val = temp[j].value2;
                if (minHeap.size() < k) {
                    minHeap.add(val);
                    sum += val;
                } else if (val > (minHeap.isEmpty() ? 0 :minHeap.peek())) {
                    sum += val - (minHeap.isEmpty() ? 0 : minHeap.poll());
                    minHeap.add(val);
                }
                j++;
            }
            prefixSum[i] = sum;
        }

        long[] answer = new long[n];
        for (int i = 0; i < n; i++) {
            answer[temp[i].originIdx] = prefixSum[i];
        }
        return answer;
    }

    static class Triple {
        int originIdx, value1, value2;

        public Triple(int value1, int value2, int originIdx) {
            this.value1 = value1;
            this.value2 = value2;
            this.originIdx = originIdx;
        }
    }
}
