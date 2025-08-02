package Contest.Weekly.ct442;

public class MaximumContainersOnAShip {
    public int getMaxWeight(int n, int w, int weight) {
        return Math.min(n * n, weight / w);
    }
}
