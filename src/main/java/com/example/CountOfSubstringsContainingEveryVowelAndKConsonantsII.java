package com.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CountOfSubstringsContainingEveryVowelAndKConsonantsII {
    private static final Set<Character> VOWELS = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public long countOfSubstrings(String word, int k) {
        int n = word.length();
        int[] freqVowels = new int[5];
        int countVowels = 0;
        int start = 0, countConsonants = 0, firstIndexValid = -1;
        long result = 0;

        for (int end = 0; end < n; end++) {
            char ch = word.charAt(end);
            if (VOWELS.contains(ch)) {
                int idx = "aeiou".indexOf(ch);
                if (freqVowels[idx] == 0) countVowels++;
                freqVowels[idx]++;
            } else {
                countConsonants++;
                if (firstIndexValid == -1) firstIndexValid = end;
            }
            while (countConsonants > k) {
                char startChar = word.charAt(start);
                if (VOWELS.contains(startChar)) {
                    int idx = "aeiou".indexOf(startChar);
                    freqVowels[idx]--;
                    if (freqVowels[idx] == 0) countVowels--;
                } else {
                    countConsonants--;
                    firstIndexValid = start + 1;
                }
                start++;
            }
            if (countConsonants == k && countVowels >= 5) {
                result += firstIndexValid - start + 1;
            }
        }
        return (countVowels == n && k == 0) ? 1 : result;
    }

    public static void main(String[] args) {
        CountOfSubstringsContainingEveryVowelAndKConsonantsII sol = new CountOfSubstringsContainingEveryVowelAndKConsonantsII();
        System.out.println(sol.countOfSubstrings("aeiou", 0));
    }
}
