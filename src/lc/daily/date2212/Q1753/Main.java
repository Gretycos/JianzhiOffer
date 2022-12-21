package lc.daily.date2212.Q1753;

class Solution {
    public int maximumScore(int a, int b, int c) {
        int sum = a + b + c;
        int max = Math.max(a,Math.max(b,c));
        return Math.min(sum - max, sum / 2);
    }
}

public class Main {
}
