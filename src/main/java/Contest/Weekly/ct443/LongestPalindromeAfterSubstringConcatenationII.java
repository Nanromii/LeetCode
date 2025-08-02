package Contest.Weekly.ct443;

import java.util.HashSet;
import java.util.Set;

public class LongestPalindromeAfterSubstringConcatenationII {
    public int longestPalindrome(String s, String t) {
        Set<String> substrS = getAllSubstrings(s);
        Set<String> substrT = getAllSubstrings(t);
        int maxLength = 0;
        for (String sub : substrS) {
            if (isPalindrome(sub)) maxLength = Math.max(maxLength, sub.length());
        }
        for (String sub : substrT) {
            if (isPalindrome(sub)) maxLength = Math.max(maxLength, sub.length());
        }
        for (String subS : substrS) {
            for (String subT : substrT) {
                String combined1 = subS + subT;
                String combined2 = subT + subS;
                if (isPalindrome(combined1)) maxLength = Math.max(maxLength, combined1.length());
                if (isPalindrome(combined2)) maxLength = Math.max(maxLength, combined2.length());
            }
        }
        return maxLength;
    }

    private Set<String> getAllSubstrings(String str) {
        int n = str.length();
        Set<String> substrings = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                substrings.add(str.substring(i, j + 1));
            }
        }
        return substrings;
    }
    private boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        LongestPalindromeAfterSubstringConcatenationII l = new LongestPalindromeAfterSubstringConcatenationII();
        System.out.println(l.longestPalindrome("i", "ih"));
    }
}
