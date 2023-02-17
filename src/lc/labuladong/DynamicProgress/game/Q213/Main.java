package lc.labuladong.DynamicProgress.game.Q213;

class Solution{
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        // 首尾房间不能同时被抢
        // 1.同时不抢
        // 2.抢首不抢尾
        // 3.抢尾不抢首
        // 但是1一定小于2 or 3
        // 所以只用考虑2 or 3
        return Math.max(
                robRange(nums, 0, n-2),
                robRange(nums, 1, n-1)
        );
    }
    // 计算[start, end]的最优结果
    private int robRange(int[] nums, int start, int end) {
        int n = nums.length;
        // 定义：dp[i]为从第i间开始抢劫，最多能抢到的数目
        // dp[i+1] dp[i+2]
        int dp_i_1 = 0, dp_i_2 = 0;
        // dp[i]
        int dp_i = 0;
        for (int i = end; i >= start; i--) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}
public class Main {
}
