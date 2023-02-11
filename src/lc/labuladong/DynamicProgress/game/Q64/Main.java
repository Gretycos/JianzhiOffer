package lc.labuladong.DynamicProgress.game.Q64;

class Solution {
    private int[][] memo;
    private int m, n;
    // 自顶向下
    public int minPathSum(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        memo = new int[m][n];
        return dp(grid,m-1, n-1);
    }

    private int dp(int[][] grid, int i, int j){
        if (i < 0 || j < 0) return Integer.MAX_VALUE;
        if (i == 0 && j == 0) return grid[0][0];
        if (memo[i][j] != 0) return memo[i][j];
        memo[i][j] = Math.min(dp(grid, i, j-1), dp(grid, i-1, j)) + grid[i][j];
        return memo[i][j];
    }

    // 自底向上
    public int minPathSum2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    // 空间压缩
    public int minPathSum3(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n+1];
        dp[0] = Integer.MAX_VALUE;
        dp[1] = grid[0][0];
        for (int j = 2; j <= n; j++) {
            dp[j] = dp[j-1] + grid[0][j-1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j-1];
            }
        }
        return dp[n];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(solution.minPathSum(grid));
    }
}
