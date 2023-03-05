package lc.labuladong.Others.Math.Q172;

class Solution {
    public long trailingZeroes(long n) {
        long res = 0;
        for (long d = n; d / 5 > 0; d /= 5) {
            res += d / 5;
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.trailingZeroes(4000000019L));
    }
}
