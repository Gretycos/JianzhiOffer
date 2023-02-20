package lc.labuladong.DynamicProgress.game.Q714;

class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        // base case
        dp[0][1] = - prices[0] - fee;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i] - fee);
        }
        return dp[n-1][0];
    }

    // 空间压缩
    public int maxProfit2(int[] prices, int fee) {
        int dp_i_0 = 0, dp_i_1 = -prices[0] - fee;
        for (int i = 1; i < prices.length; i++) {
            int t = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, t - prices[i] - fee);
        }
        return dp_i_0;
    }
}

public class Main {
}
