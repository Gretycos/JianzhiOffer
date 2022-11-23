package lc.daily.date2211.Q1742;

class Solution {
    public int countBalls(int lowLimit, int highLimit) {
        int[] counts = new int[46];
        int res = 0;
        for (int i = lowLimit; i <= highLimit; i++) {
            int idx = 0, x = i;
            while (x > 0){
                idx += x % 10;
                x /= 10;
            }
            counts[idx] ++;
            if (counts[idx] > res) res = counts[idx];
        }
        return res;
    }
}
public class Main {
}
