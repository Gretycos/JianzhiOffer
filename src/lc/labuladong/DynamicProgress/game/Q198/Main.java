package lc.labuladong.DynamicProgress.game.Q198;

import java.util.Arrays;

class Solution {
    private int[] nums;
    private int[] memo;
    public int rob(int[] nums) {
        this.nums = nums;
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(0);
    }
    // 定义：dp(i) 为 从nums[i..]能获得的最大价值
    private int dp(int i){
        // base case
        if (i >= nums.length) return 0;

        if (memo[i] != -1) return memo[i];

        int res = Math.max(
                dp(i+1), // 本轮不拿
                nums[i] + dp(i+2) // 本轮拿
        );

        memo[i] = res;
        return res;
    }

    public int rob2(int[] nums) {
        int n = nums.length;
        // 定义：dp[i]为从第i间开始抢劫，最多能抢到的数目
        int[] dp = new int[n+2];
        for (int i = n-1; i >= 0; i--) {
            dp[i] = Math.max(dp[i+1], nums[i] + dp[i+2]);
        }
        return dp[0];
    }

    public int rob3(int[] nums) {
        int n = nums.length;
        // 定义：dp[i]为从第i间开始抢劫，最多能抢到的数目
        // dp[i+1] dp[i+2]
        int dp_i_1 = 0, dp_i_2 = 0;
        // dp[i]
        int dp_i = 0;
        for (int i = n-1; i >= 0; i--) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}
public class Main {
}
