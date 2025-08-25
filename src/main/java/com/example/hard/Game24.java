package com.example.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game24 {
    private final char[] OPERATORS = {'+', '-', '*', '/'};
    private boolean isValid = false;

    public boolean judgePoint24(int[] cards) {
        List<Double> numbers = Arrays.stream(cards).asDoubleStream().boxed().toList();
        backtrack(numbers);
        return isValid;
    }

    private void backtrack(List<Double> numbers) {
        if (numbers.size() == 1) {
            isValid = Math.abs(24 - numbers.get(0)) < 0.01;
            return;
        }
        if (isValid) return;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                if (i == j) continue;
                List<Double> next = new ArrayList<>();
                for (int k = 0; k < numbers.size(); k++) {
                    if (k != i && k != j) next.add(numbers.get(k));
                }
                for (char operator : OPERATORS) {
                    if (operator == '/' && Math.abs(numbers.get(j)) < 0.001) continue;
                    double result = getResultOfOperation(numbers.get(i), numbers.get(j), operator);
                    next.add(result);
                    backtrack(next);
                    next.remove(next.size() - 1);
                }
            }
        }
    }

    private double getResultOfOperation(double a, double b, char operator) {
        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            default -> a / b;
        };
    }
}
