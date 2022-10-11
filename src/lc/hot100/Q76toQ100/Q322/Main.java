package lc.hot100.Q76toQ100.Q322;

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 *
 * 示例1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 *
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 *
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 * 提示：
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 *
 * */
class Solution {
    // dp
    // 定义：dp[i]：组成金额i所需最少的硬币数量
    // 转移：dp[i] = min(j=0,coins.length-1){dp[i], dp[i-coin[j]] + 1}
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        // 快2ms但是不好理解
//        for (int coin : coins) {
//            for (int val = coin; val <= amount ; val++) {
//                dp[val] = Math.min(dp[val], dp[val - coin] + 1);
//            }
//        }

        for (int val = 1; val <= amount; val++) {
            for (int coin : coins) {
                if (coin <= val){
                    dp[val] = Math.min(dp[val], dp[val - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] coins = {1,2,5};
        solution.coinChange(coins,11);
    }
}
