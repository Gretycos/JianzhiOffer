package lc.labuladong.DynamicProgress.game.Q312;

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        // 添加两侧虚拟气球
        int[] points = new int[n+2];
        points[0] = points[n+1] = 1;
        System.arraycopy(nums,0,points,1,n);

        // 定义：dp[i][j] = x ： 戳破开区间(i,j)之间的所有气球，得到的最高分数为x
        int[][] dp = new int[n+2][n+2];
        // base case
        // dp[i][j] = 0, 0 <= i <= n + 1 && j <= i + 1
        // 这种情况下，(i,j)中间没有气球

        // 根据返回索引（矩阵右上角），确定遍历方向：从下到上，从左到右
        for (int i = n; i >= 0; i--) {
            for (int j = i + 1; j < n + 2; j++) {
                // 枚举最后戳破的气球 k (i < k < j)
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(
                            dp[i][j],
                            dp[i][k] + dp[k][j] + points[i] * points[k] * points[j]
                    );
                }
            }
        }
        return dp[0][n+1];
    }
}

public class Main {
}
