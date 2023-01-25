package lc.labuladong.DynamicProgress.dp.Q53;

class Solution {
    // 没有优化空间的dp
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        // 定义：dp[i]是以nums[i]结尾的最大子数组和
        int[] dp = new int[n];

        // base case
        // 第一个元素前面没有子数组
        dp[0] = nums[0];

        // 状态转移
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i-1]);
        }
        // 得到nums的最大子数组
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 优化空间
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        // base case
        int dp_0 = nums[0];
        int dp_1, res = dp_0;

        for (int i = 1; i < n; i++) {
            dp_1 = Math.max(nums[i], nums[i] + dp_0);
            dp_0 = dp_1;
            res = Math.max(res, dp_1);
        }

        return res;
    }

    // 滑动窗口
    public int maxSubArray3(int[] nums) {
        int left = 0, right = 0;
        int windowSum = 0, maxSum = Integer.MIN_VALUE;
        while (right < nums.length){
            windowSum += nums[right];
            right++;
            maxSum = Math.max(maxSum, windowSum);
            while (windowSum < 0){
                windowSum -= nums[left];
                left++;
            }
        }
        return maxSum;
    }

    // 前缀和
    public int maxSubArray4(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        // 构造前缀和
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int res = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE; // 前缀最小值
        for (int i = 0; i < n; i++) {
            minVal = Math.min(minVal, preSum[i]);
            res = Math.max(res, preSum[i + 1] - minVal);
        }
        return res;
    }
}
public class Main {
}
