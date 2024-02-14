package lc.dp.Q53;

import java.util.Arrays;

/**
 * @Author Tsong
 * @Date 2023/9/14 18:25
 */

class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // dp[i]：i结尾的最大子数组和
        int[] dp = new int[n];
        // base
        dp[0] = nums[0];

        int res = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 降维
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        // dp[i]：i结尾的最大子数组和
        int dp = nums[0];

        int res = dp;
        for (int i = 1; i < n; i++) {
            dp = Math.max(nums[i], dp + nums[i]);
            res = Math.max(res, dp);
        }
        return res;
    }

}


public class Main {
}
