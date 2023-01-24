package lc.daily.date2301.Q1828;

class Solution {
    public int[] countPoints(int[][] points, int[][] queries) {
        int[] res = new int[queries.length];
        for (int i = 0; i < res.length; i++) {
            int[] query = queries[i];
            int xq = query[0], yq = query[1], rq = query[2];
            for (int[] point : points) {
                int dx = point[0] - xq, dy = point[1] - yq;
                if (dx * dx + dy * dy <= rq * rq){
                    res[i]++;
                }
            }
        }
        return res;
    }
}

public class Main {
}
