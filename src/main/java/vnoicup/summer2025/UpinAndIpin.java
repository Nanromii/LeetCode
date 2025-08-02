package vnoicup.summer2025;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UpinAndIpin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = Integer.parseInt(sc.nextLine());
        while (q-- > 0) {
            String s = sc.nextLine();
            int n = s.length();
            Map<Integer, Integer> map1 = new HashMap<>();
            Map<Integer, Integer> map2 = new HashMap<>();
            map1.put(0, -1);
            map2.put(0, -1);
            int sum1 = 0, sum2 = 0;
            boolean found = false;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                sum1 += (c == 'U' ? 1 : -2);
                if (!found && map1.containsKey(sum1)) {
                    int j = map1.get(sum1);
                    System.out.println("YES " + (j + 2) + " " + (i + 1));
                    found = true;
                    break;
                }
                map1.putIfAbsent(sum1, i);
                sum2 += (c == 'U' ? -2 : 1);
                if (!found && map2.containsKey(sum2)) {
                    int j = map2.get(sum2);
                    System.out.println("YES " + (j + 2) + " " + (i + 1));
                    found = true;
                    break;
                }
                map2.putIfAbsent(sum2, i);
            }
            if (!found) {
                System.out.println("NO");
            }
        }
    }
}
