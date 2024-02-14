package lc.dp.Q300;

import java.util.Arrays;

/**
 * @Author Tsong
 * @Date 2023/9/26 20:15
 */
class Solution {
    private int[] nums;
    private int[] memo;
    private int max;
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;
        this.nums = nums;
        memo = new int[n];
        Arrays.fill(memo, -1);
        dp(n-1);
        return max;
    }

    private int dp(int i) {
        if (i == 0) return 1;
        if (memo[i] != -1) return memo[i];

        int res = 0;
        for (int j = i-1; j >= 0; j--) {
            int subProblem = dp(j);
            res = Math.max(res, (nums[j] >= nums[i] ? 0 : subProblem) + 1);
        }
        max = Math.max(max, memo[i]);
        memo[i] = res;
        return memo[i];
    }

    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,3,6,7,9,4,10,5,6};
        System.out.println(solution.lengthOfLIS(nums));
    }
}
