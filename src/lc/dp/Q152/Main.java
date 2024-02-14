package lc.dp.Q152;

/**
 * @Author Tsong
 * @Date 2023/9/17 21:17
 */
class Solution {
    private int[] nums;
    private int[] memo;
    private int res = Integer.MIN_VALUE;
    public int maxProduct(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        dp(n-1);
        return res;
    }

    // dp(i)[0]：最大值，dp(i)[1]：最小值
    private int[] dp(int i) {
        if (i < 0) return new int[]{1, 1};
        if (memo != null) return memo;
        int max = Math.max(nums[i], nums[i] > 0 ? dp(i-1)[0] * nums[i] : dp(i-1)[1] * nums[i]);
        int min = Math.min(nums[i], nums[i] > 0 ? dp(i-1)[1] * nums[i] : dp(i-1)[0] * nums[i]);
        res = Math.max(res, max);
        memo = new int[]{max, min};
        return memo;
    }

    public int maxProduct2(int[] nums) {
        int[] dp = new int[]{1,1};
        int res = Integer.MIN_VALUE;
        for (int num : nums) {
            int maxCur = Math.max(num, num > 0 ? num * dp[0] : num * dp[1]);
            int minCur = Math.min(num, num > 0 ? num * dp[1] : num * dp[0]);
            dp = new int[]{maxCur, minCur};
            res = Math.max(res, maxCur);
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-2};
        System.out.println(solution.maxProduct(nums));
    }
}
