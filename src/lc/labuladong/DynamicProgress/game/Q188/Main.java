package lc.labuladong.DynamicProgress.game.Q188;

class Solution {
    public int maxProfit(int K, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        // k超过n/2说明k是无限的
        if (K > n / 2){
            return maxProfit_k_inf(prices);
        }
        int[][][] dp = new int[n][K + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = K; k >= 1; k--) {
                if (i - 1 == -1){
                    // base case
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        }
        return dp[n-1][K][0];
    }

    private int maxProfit_k_inf(int[] prices) {
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int price : prices) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, temp - price);
        }
        return dp_i_0;
    }
}

public class Main {
}
