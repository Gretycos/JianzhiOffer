package lc.labuladong.DataStructure.dp.Q494;

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // sum(A) - sum(B) = target
        // sum(A) = target + sum(B)
        // sum(A) + sum(A) = target + sum(B) + sum(A)
        // 2 * sum(A) = target + sum(nums)
        if (sum < Math.abs(target) || (sum + target) % 2 == 1){
            return 0;
        }
        return subsets(nums, (sum + target) / 2);
    }

    // 0-1背包
    private int subsets2(int[] nums, int sum){
        int n = nums.length;
        int[][] dp = new int[n+1][sum+1];
        // base case
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j - nums[i - 1] >= 0){
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];
    }
    // 空间优化
    private int subsets(int[] nums, int sum){
        int n = nums.length;
        int[] dp = new int[sum+1];
        // base
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= 0; j--) {
                if (j - nums[i] >= 0){
                    dp[j] = dp[j] + dp[j - nums[i]];
                }
            }
        }
        return dp[sum];
    }
}

public class Main {
}
