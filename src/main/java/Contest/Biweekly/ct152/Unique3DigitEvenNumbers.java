package Contest.Biweekly.ct152;

public class Unique3DigitEvenNumbers {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int digit : digits) {
            freq[digit]++;
        }
        int count = 0;
        for (int lastDigit = 0; lastDigit <= 8; lastDigit += 2) {
            if (freq[lastDigit] == 0) continue;
            freq[lastDigit]--;
            for (int firstDigit = 1; firstDigit <= 9; firstDigit++) {
                if (freq[firstDigit] == 0) continue;
                freq[firstDigit]--;
                for (int secondDigit = 0; secondDigit <= 9; secondDigit++) {
                    if (freq[secondDigit] > 0) {
                        count++;
                    }
                }
                freq[firstDigit]++;
            }
            freq[lastDigit]++;
        }
        return count;
    }
}
