package lc.hot100.Q76toQ100.Q416;

/**
 * 给你一个 只包含正整数 的 非空 数组nums 。
 * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 *
 *
 * 示例 2：
 *
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 *
 * */

class Solution {
    // 0-1背包
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) return false;
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) return false;
        int avg = sum / 2;
        if (maxNum > avg) return false;

        boolean[] dp = new boolean[avg+1];
        dp[0] = true;
        for (int i = 1; i < n; i++) {
            for (int j = avg; j >= nums[i]; j--) {
                dp[j] |= dp[j-nums[i]];
            }
        }

        return dp[avg];
    }
}

public class Main {
}
