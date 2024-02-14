package lc.dp.Q213;

import java.util.Arrays;

/**
 * @Author Tsong
 * @Date 2023/9/25 09:15
 */
class Solution {
    private int[] memo;
    private int[] nums;
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        this.nums = nums;
        memo = new int[n];
        Arrays.fill(memo, -1);
        int res1 = dp(1, n-1);
        Arrays.fill(memo, -1);
        int res2 = dp(0, n-2);
        return Math.max(res1, res2);
    }

    private int dp(int start, int end) {
        if (end < start) return 0;
        if (memo[end] != -1) return memo[end];

        int robI = dp(start, end-2) + nums[end];
        int notRobI = dp(start, end-1);

        memo[end] = Math.max(robI, notRobI);
        return memo[end];
    }

    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        this.nums = nums;
        return Math.max(robRange(0, n-2), robRange(1, n-1));
    }

    private int robRange(int start, int end) {
        int dp_i = 0, dp_i_1 = 0, dp_i_2 = 0;
        for (int i = start; i <= end; i++) {
            dp_i = Math.max(dp_i_1, dp_i_2 + nums[i]);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}

public class Main {
}
