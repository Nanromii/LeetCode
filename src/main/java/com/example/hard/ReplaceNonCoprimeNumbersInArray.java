package com.example.hard;

import java.util.*;

public class ReplaceNonCoprimeNumbersInArray {
    public static void main(String[] args) {
    }

    public List<Integer> replaceNonCoprimes(int[] nums) {
        Stack<Integer> st = new Stack<>();
        for (int num : nums) {
            st.add(num);
            while (st.size() >= 2) {
                int num2 = st.pop(), num1 = st.pop();
                int gcd = GCD(Math.min(num1, num2), Math.max(num1, num2));
                if (gcd != 1) {
                    st.add(LCM(num1, num2, gcd));
                } else {
                    st.add(num1);
                    st.add(num2);
                    break;
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!st.isEmpty()) {
            result.add(st.pop());
        }
        Collections.reverse(result);
        return result;
    }

    private int LCM(int a, int b, int GCD) {
        if (a == b) return b;
        return a / GCD * b;
    }

    private int GCD(int a, int b) {
        if (a == 0 || a == b) return b;
        while (a != 0) {
            int r = b % a;
            b = a;
            a = r;
        }
        return b;
    }
}
