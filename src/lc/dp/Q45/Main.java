package lc.dp.Q45;

import java.util.Arrays;

/**
 * @Author Tsong
 * @Date 2023/9/14 16:53
 */

class Solution {
    private int n;
    private int[] memo;
    public int jump2(int[] nums) {
        n = nums.length;
        memo = new int[n];
        Arrays.fill(memo, n);
        return dp(nums, 0);
    }

    // 定义dp：从start到n-1最少用dp(nums, start)步
    private int dp(int[] nums, int start) {
        if (start >= n - 1) return 0;
        // 备忘录
        if (memo[start] != n){
            return memo[start];
        }
        int steps = nums[start];
        // 选择跳1..steps步
        for (int i = 1; i <= steps; i++) {
            int subProblem = dp(nums, start + i);
            memo[start] = Math.min(memo[start], subProblem + 1);
        }

        return memo[start];
    }

    public int jump(int[] nums) {
        n = nums.length;
        int lastFarthest = 0; // 上一次的farthest
        int farthest = 0; // 当前能跳到的最远的地方
        int step = 0;
        for (int i = 0; i < n - 1; i++) { // i 不需要=n-1，因为n-1已经是终点
            farthest = Math.max(farthest, i + nums[i]);
            if (i == lastFarthest){
                step ++;
                lastFarthest = farthest;
            }
        }
        return step;
    }
}

public class Main {
}
