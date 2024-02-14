package lc.dp.Q322;

import java.util.Arrays;

/**
 * @Author Tsong
 * @Date 2023/10/6 22:58
 */

class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        // dp[i][j]: 用前i个硬币可以表示数额j所需的最少硬币数
        int[][] dp = new int[n+1][amount+1];
        // base
        Arrays.fill(dp[0], amount + 1);
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i-1] >= 0) {
                    // 剩余金额 >= 0
                    dp[i][j] = Math.min(
                            dp[i-1][j], // 不选coin[i-1]
                            dp[i][j-coins[i-1]] + 1 // 选coin[i-1]
                    );
                } else {
                    // 剩余金额 < 0
                    dp[i][j] = dp[i-1][j]; // 不选coin[i-1]
                }
            }
        }
        return dp[n][amount] > amount ? -1 : dp[n][amount];
    }

    public int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        // dp[i][j]: 用前i个硬币可以表示数额j所需的最少硬币数
        int[] dp = new int[amount+1];
        // base
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i-1] >= 0) {
                    // 剩余金额 >= 0
                    dp[j] = Math.min(
                            dp[j], // 不选coin[i-1]
                            dp[j-coins[i-1]] + 1 // 选coin[i-1]
                    );
                } else {
                    // 剩余金额 < 0
                    dp[j] = dp[j]; // 不选coin[i-1]
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    private int[] coins;
    private int[] memo;
    public int coinChange3(int[] coins, int amount) {
        this.coins = coins;
        // dp[i][j]: 用前i个硬币可以表示数额j所需的最少硬币数
        memo = new int[amount + 1];
        Arrays.fill(memo, -2);
        return dp(amount);
    }

    private int dp(int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (memo[amount] != -2) return memo[amount];

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = dp(amount - coin);
            if (subProblem == -1) continue;
            res = Math.min(res, subProblem + 1);
        }
        memo[amount] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[amount];
    }

}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] coins = {1,2,5};
        int amount = 11;
        System.out.println(solution.coinChange(coins, amount));
    }
}
