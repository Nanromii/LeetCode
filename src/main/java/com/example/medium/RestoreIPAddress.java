package com.example.medium;

import java.util.*;

public class RestoreIPAddress {
    private List<String> result;

    public List<String> restoreIpAddresses(String s) {
        result = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) return result;
        Deque<String> deque = new ArrayDeque<>();
        helper(deque, 0, 0, s, s.length());
        return result;
    }

    private void helper(Deque<String> deque, int start, int seg, String s, int length) {
        if (start == length && seg == 4) {
            result.add(String.join(".", deque));
            return;
        }
        int remaining = length - start, minRemaining = 4 - seg, maxRemaining = 3 * (4 - seg);
        if (remaining < minRemaining || remaining > maxRemaining) return;
        for (int i = 1; i <= 3; i++) {
            if (start + i > length) break;
            if (i > 1 && s.charAt(start) == '0') break;
            int val = 0;
            for (int k = 0; k < i; k++) {
                val = val * 10 + (s.charAt(start + k) - '0');
            }
            if (val > 255) continue;
            deque.add(s.substring(start, start + i));
            helper(deque, start + i, seg + 1, s, length);
            deque.removeLast();
        }
    }
}
