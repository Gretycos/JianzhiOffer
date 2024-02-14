package lc.dp.Q198;

import java.util.Arrays;

/**
 * @Author Tsong
 * @Date 2023/9/25 08:59
 */
class Solution {

    private int[] memo;
    private int[] nums;
    public int rob(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        memo = new int[n];
        Arrays.fill(memo, -1);
        return dp(n-1);
    }

    private int dp(int i) {
        if (i < 0) return 0;
        if (memo[i] != -1) return memo[i];

        int robI = dp(i-2) + nums[i];
        int notRobI = dp(i-1);

        memo[i] = Math.max(robI, notRobI);
        return memo[i];
    }

    public int rob2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (i - 1 < 0) {
                dp[i] = nums[i];
                continue;
            }
            if (i - 2 < 0){
                dp[i] = Math.max(dp[i-1], nums[i]);
                continue;
            }
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[n-1];
    }

    public int rob3(int[] nums) {
        int n = nums.length;
        int dp_i = 0, dp_i_1 = 0, dp_i_2 = 0;

        for (int i = 0; i < n; i++) {
            dp_i = Math.max(dp_i_1, dp_i_2 + nums[i]);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}

public class Main {
}
