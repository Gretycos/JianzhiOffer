package lc.daily.date2211.Q813;

class Solution {
    private int n;
    private int[] preSum;
    private double[][] cache;
    public double largestSumOfAverages(int[] nums, int k) {
        n = nums.length;
        preSum = new int[n + 1];
        cache = new double[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        return dfs(0,k);
    }

    // 求[i,n]的最大均值
    private double dfs(int i, int k){
        if (i == n) return 0;
        if (k == 1) return (preSum[n] - preSum[i]) * 1.0 / (n - i);
        if (cache[i][k] > 0) return cache[i][k];
        double max = 0.0;
        for (int j = i + 1; j <= n; j++) {
            // 求区间[i, j)的均值 + [j,n]的均值
            double t = (preSum[j] - preSum[i]) * 1.0 / (j - i) + dfs(j, k - 1);
            max = Math.max(max,t);
        }
        cache[i][k] = max;
        return max;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {9,1,2,3,9};
        System.out.println(solution.largestSumOfAverages(nums,3));
    }
}
