package lc.hot100.Q76toQ100.Q312;

import java.util.Arrays;

/**
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组nums中。
 *
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
 * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。
 * 如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 *
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 *
 *
 * 示例 2：
 *
 * 输入：nums = [1,5]
 * 输出：10
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 300
 * 0 <= nums[i] <= 100
 *
 * */

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        // 哨兵气球
        int[] points = new int[n+2];
        points[0] = points[n+1] = 1;
        System.arraycopy(nums,0,points,1,n);
        // 把全程看作添加气球
        // 定义 dp：dp[i][j]为戳破(i,j)内的气球能够获得的最高分数
        //      定义 k (i < k < j) 为区间内最后一个被戳爆的气球
        int[][] dp = new int[n+2][n+2];

        for (int i = n-1; i >= 0 ; i--) {
            for (int j = i+2; j < n+2 ; j++) {
                // i < k < j，枚举k的可能
                for (int k = i+1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j],
                            dp[i][k] + dp[k][j] + points[i] * points[k] * points[j]);
                }
            }
        }
        return dp[0][n+1];
    }

    // 很慢
    private int[][] dp;
    private int[] points;
    public int maxCoins2(int[] nums) {
        int n = nums.length;
        points = new int[n+2];
        System.arraycopy(nums,0,points,1,n);
        points[0] = points[n+1] = 1;
        dp = new int[n+2][n+2];
        for (int[] row : dp) {
            Arrays.fill(row,-1);
        }
        return dfs(0,n+1);
    }

    private int dfs(int start, int end){
        if (end - start <= 1){
            return 0;
        }

        if (dp[start][end] != -1){
            return dp[start][end];
        }

        for(int i = start + 1; i < end; i++){
            dp[start][end] = Math.max(dp[start][end],
                    dfs(start,i) + dfs(i,end) + points[start] * points[i] * points[end]);
        }
        return dp[start][end];
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3,1,5,8};
        System.out.println(solution.maxCoins2(nums));
    }
}
