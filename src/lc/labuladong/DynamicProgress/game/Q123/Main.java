package lc.labuladong.DynamicProgress.game.Q123;

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int K = 2;
        // 定义：dp[i][k][0]：第i天最大操作次数为k，不持有的最大收益
        //      dp[i][k][1]：第i天最大操作次数为k，持有的最大收益
        int[][][] dp = new int[n][K+1][2];
        for (int i = 0; i < n; i++) {
            // 正反遍历结果都一样
            // dp[i][k][..] 不会依赖 dp[i][k - 1][..]，而是依赖 dp[i - 1][k - 1][..]，
            // 而 dp[i - 1][..][..]，都是已经计算出来的
            // 所以可以使用反着遍历
            // 而随着「状态」的推移，你会进行交易，那么交易次数上限 k 应该不断减少
            // 反着遍历逻辑更通顺
            for (int k = K; k >= 1; k--) {
                if (i - 1 == -1){
                    dp[i][k][0] = 0;
                    dp[i][k][1] = - prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        }
        return dp[n-1][K][0];
    }

    public int maxProfit2(int[] prices) {
        int dp_i_2_0 = 0, dp_i_2_1 = Integer.MIN_VALUE;
        int dp_i_1_0 = 0, dp_i_1_1 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i_2_0 = Math.max(dp_i_2_0, dp_i_2_1 + price);
            dp_i_2_1 = Math.max(dp_i_2_1, dp_i_1_0 - price);
            dp_i_1_0 = Math.max(dp_i_1_0, dp_i_1_1 + price);
            dp_i_1_1 = Math.max(dp_i_1_1, - price);
        }
        return dp_i_2_0;
    }
}

public class Main {
}
