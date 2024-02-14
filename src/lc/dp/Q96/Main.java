package lc.dp.Q96;

/**
 * @Author Tsong
 * @Date 2023/9/16 14:26
 */
class Solution {

    private int[][] memo;
    public int numTrees(int n) {
        memo = new int[n+1][n+1];
        return dp(1, n);
    }

    private int dp(int lo, int hi) {
        if (lo > hi) return 1;
        if (memo[lo][hi] != -1) return memo[lo][hi];

        int count = 0;
        for (int mid = lo; mid <= hi; mid++) {
            int left = dp(lo, mid - 1);
            int right = dp(mid + 1, hi);
            count += left * right;
        }
        memo[lo][hi] = count;
        return memo[lo][hi];
    }
}
public class Main {
}
