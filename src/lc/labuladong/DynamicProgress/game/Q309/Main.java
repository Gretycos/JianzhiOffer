package lc.labuladong.DynamicProgress.game.Q309;

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        // 转移方程的i-1, i-2为负数时要特殊讨论
        // base case 1
        dp[0][1] = - prices[0];
        // base case 2
        if (n > 1){
            dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
            dp[1][1] = Math.max(dp[0][1], - prices[1]);
        }

        for (int i = 2; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
        }

        return dp[n-1][0];
    }

    // 空间压缩
    public int maxProfit2(int[] prices) {
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE, dp_i_2 = 0;
        for (int price : prices) {
            int t = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, dp_i_2 - price);
            dp_i_2 = t;
        }
        return dp_i_0;
    }
}

public class Main {
}
