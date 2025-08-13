package com.example.medium;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RangeProductQueriesOfPowers {
    private final static int MOD = 1_000_000_007;
    public int[] productQueries(int n, int[][] queries) {
        int[] powers = getPowers(n);
        BigInteger[] product = new BigInteger[powers.length];
        product[0] = BigInteger.valueOf(powers[0]);
        for (int i = 1; i < powers.length; i++) {
            product[i] = product[i - 1].multiply(BigInteger.valueOf(powers[i]));
        }
        int[] result = new int[queries.length];
        int i = 0;
        for (int[] query : queries) {
            BigInteger left = BigInteger.ONE;
            BigInteger right = product[query[1]];
            if (query[0] > 0) left = product[query[0] - 1];
            result[i++] = right.divide(left).mod(BigInteger.valueOf(MOD)).intValue();
        }
        return result;
    }

    private int[] getPowers(int n) {
        List<Integer> powers = new ArrayList<>();
        char[] bin = toBinary(n).toCharArray();
        for (int i = 0 ; i < bin.length; i++) {
            if (bin[i] == '1') {
                powers.add(1 << i);
            }
        }
        int[] result = new int[powers.size()];
        for (int i = 0; i < powers.size(); i++) {
            result[i] = powers.get(i);
        }
        return result;
    }

    private String toBinary(int n) {
        StringBuilder bin = new StringBuilder();
        while (n > 0) {
            int mod = n % 2;
            bin.append((char) (mod + '0'));
            n /= 2;
        }
        return bin.toString();
    }
}
