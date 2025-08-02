package Contest.Biweekly.ct153;

public class ReserveDegreeAString {
    public int reverseDegree(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += (26 - (s.charAt(i) - 'a')) * i;
        }
        return sum;
    }
}
