package lc.labuladong.DataStructure.dp.Q518;

class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        // base case
        // dp[0][..] = 0
        // dp[0][0]表示不选择任何硬币也能凑到0元，所以是一种方法
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] >= 0){
                    dp[i][j] = dp[i-1][j] // 继承上一轮
                                + dp[i][j - coins[i - 1]]; // 说明可重复使用i
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][amount];
    }

    // 优化空间
    public int change2(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount+1];
        // base case
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i] >= 0){
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }
        return dp[amount];
    }
}

public class Main {
}
