package Contest.Weekly.ct444;

public class MinimumPairRemovalToSortArrayI {
    public int minimumPairRemoval(int[] nums) {
        if (isNonDecreasing(nums)) return 0;
        int sum = Integer.MAX_VALUE, index = -1, count = 0;
        while (!isNonDecreasing(nums)) {
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] + nums[i] < sum) {
                    sum = nums[i] + nums[i + 1];
                    index = i;
                }
            }
            nums[index] = nums[index + 1] = sum;
            count++;
        }
        return count;
    }

    class Triple {
        int first, second, index;
        public Triple(int first, int second, int index) {
            this.first = first;
            this.second = second;
            this.index = index;
        }
    }

    private boolean isNonDecreasing(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) return false;
        }
        return true;
    }
}
