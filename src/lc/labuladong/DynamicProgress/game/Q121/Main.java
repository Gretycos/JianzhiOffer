package lc.labuladong.DynamicProgress.game.Q121;

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 定义：dp[i][0]：第i天不持有的最大收益
        //      dp[i][1]：第i天持有的最大收益
        int[][] dp = new int[n][2];

        // base case
        dp[0][1] = - prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]); // 今日卖出
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]); // 今日买入
        }

        return dp[n-1][0];
    }

    // 空间压缩
    public int maxProfit2(int[] prices) {
        int dp_i_0 = 0, dp_i_1 = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, - prices[i]);
        }
        return dp_i_0;
    }
}

public class Main {
}
