package lc.dp.Q122;

/**
 * @Author Tsong
 * @Date 2023/9/16 21:44
 */
class Solution {
    public int maxProfit(int[] prices) {
        int m = prices.length;
        // dp[i][0/1]=第i天持有/不持有的利润
        int[][] dp = new int[m][2];
        for (int i = 0; i < m; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = - prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[m-1][0];
    }
}

public class Main {
}
