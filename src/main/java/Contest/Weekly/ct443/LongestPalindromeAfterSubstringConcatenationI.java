package Contest.Weekly.ct443;

public class LongestPalindromeAfterSubstringConcatenationI {
    public int longestPalindrome(String s, String t) {
        int n = s.length(), m = t.length();
        int maxLength = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j < n; j++) {
                for (int x = 0; x < m; x++) {
                    for (int y = x - 1; y < m; y++) {
                        String str1 = j != i - 1 ? s.substring(i, j + 1) : "";
                        String str2 = y != x - 1 ? t.substring(x, y + 1) : "";
                        String candidate = str1 + str2;
                        if (isPalindrome(candidate)) {
                            maxLength = Math.max(maxLength, candidate.length());
                        }
                    }
                }
            }
        }
        return maxLength;
    }

    private boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        LongestPalindromeAfterSubstringConcatenationI l = new LongestPalindromeAfterSubstringConcatenationI();
        System.out.println(l.longestPalindrome("n", "aaaa"));
    }
}
