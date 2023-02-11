package lc.labuladong.DynamicProgress.basic.Q322;

import java.util.Arrays;

class Solution {
    // 带备忘录的dp
    private int[] memo;
    public int coinChange(int[] coins, int amount) {
        memo = new int[amount+1];
        Arrays.fill(memo, -666);
        return dp(coins,amount);
    }


    // 定义：要凑出金额 n，至少要 dp(coins, n) 个硬币
    private int dp(int[] coins, int amount){
        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        // 查询备忘录
        if (memo[amount] != -666){
            return memo[amount];
        }

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = dp(coins, amount - coin);
            // 子问题无解，跳过
            if (subProblem == -1) continue;
            // 在子问题中选择最优解，然后加1
            res = Math.min(res, subProblem + 1);
        }
        memo[amount] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[amount];
    }

    // 迭代
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        // base case
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] == amount + 1? -1 : dp[amount];
    }
}
public class Main {
}
