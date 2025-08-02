package com.example;
import java.net.URL;
import java.util.*;
public class Main {
    public static void main(String[] args) {

    }

    public long minCost(int[] basket1, int[] basket2) {
        return 0;
    }

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static String decimalTo(int number, int base) {
        if (base < 2 || base > 36) {
            throw new IllegalArgumentException("Base must be in the range 2-36");
        }
        if (number == 0) return "0";
        StringBuilder result = new StringBuilder();
        boolean isNegative = number < 0;
        number = Math.abs(number);
        while (number > 0) {
            int remainder = number % base;
            char digit = (char) (remainder < 10 ? '0' + remainder : 'A' + (remainder - 10));
            result.append(digit);
            number /= base;
        }
        if (isNegative) {
            result.append("-");
        }
        return result.reverse().toString();
    }

    private static final String[] ZERO = {"0", "00", "000"};

    private static String decimalToBinary(int num) {
        StringBuilder result = new StringBuilder();
        while (num != 0) {
            result.insert(0, num % 2);
            num /= 2;
        }
        if (result.length() % 4 != 0) {
            result.insert(0, ZERO[3 - result.length() % 4]);
        }
        return result.toString();
    }

    private static int binaryToDecimal(String num) {
        int result = 0;
        for (char c : num.toCharArray()) {
            result = 2 * result + (c - '0');
        }
        return result;
    }

    private final String TARGET = "123450";
    private final int ROWS = 2;
    private final int COLS = 3;

    public int slidingPuzzle(int[][] board) {
        String start = boardToString(board);
        Queue<State> minHeap = new PriorityQueue<>(Comparator.comparingInt(s -> s.g + s.h));
        Set<String> visited = new HashSet<>();
        minHeap.add(new State(start, start.indexOf('0'), 0, calculateHeuristic(start)));
        while (!minHeap.isEmpty()) {
            State cur = minHeap.poll();
            if (cur.board.equals(TARGET)) {
                return cur.g;
            }
            visited.add(cur.board);
            int zeroRow = cur.zeroIndex / COLS;
            int zeroCol = cur.zeroIndex % COLS;
            for (int[] direction : DIRECTIONS) {
                int newRow = zeroRow + direction[0];
                int newCol = zeroCol + direction[1];
                if (isValid(newRow, newCol)) {
                    int newZeroIndex = newRow * COLS + newCol;
                    String newBoard = swap(cur.board, cur.zeroIndex, newZeroIndex);
                    if (!visited.contains(newBoard)) {
                        int h = calculateHeuristic(newBoard);
                        minHeap.add(new State(newBoard, newZeroIndex, cur.g++, h));
                    }
                }
            }
        }
        return -1;
    }

    private String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                sb.append(num);
            }
        }
        return sb.toString();
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }

    private String swap(String s, int i, int j) {
        char[] arr = s.toCharArray();
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return new String(arr);
    }

    private int calculateHeuristic(String board) {
        int distance = 0;
        for (int i = 0; i < board.length(); i++) {
            char c = board.charAt(i);
            if (c != '0') {
                int value = c - '1';
                int targetRow = value / COLS;
                int targetCol = value % COLS;
                int currentRow = i / COLS;
                int currentCol = i % COLS;
                distance += Math.abs(targetRow - currentRow) + Math.abs(targetCol - currentCol);
            }
        }
        return distance;
    }

    class State {
        String board;
        int zeroIndex;
        int g;
        int h;

        public State(String board, int zeroIndex, int g, int h) {
            this.board = board;
            this.zeroIndex = zeroIndex;
            this.g = g;
            this.h = h;
        }
    }

    public static void generator(List<String> ans, String[] alphabet, String[] a, int i, int n) {
        if (i == n) {
            ans.add(String.join("", a));
            return;
        }
        for (String ch : alphabet) {
            a[i] = ch;
            generator(ans, alphabet, a, i + 1, n);
        }
    }

    private static final int MOD = 1000000007;

    private static long binPow(long a, long b) {
        long ans = 1;
        a = a % MOD;
        while (b > 0) {
            if (b % 2 == 1) {
                ans = (ans * a) % MOD;
            }
            a = (a * a) % MOD;
            b = b / 2;
        }
        return ans;
    }

    private void swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    public static void printArray(char[][] chars) {
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[0].length; j++) {
                System.out.print(chars[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------");
    }

    /*public static void printArray(List<List<Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }*/

    public static void printArray(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i) + " ");
        }
        System.out.println("----------");
    }

    public static void printArray(char[] c) {
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i] + " ");
        }
        System.out.println("----------");
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        System.out.println("----------");
    }

    public static void printArray(int[][] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------");
    }
}