package lc.dp.Q64;

import java.util.Arrays;

/**
 * @Author Tsong
 * @Date 2023/9/15 22:19
 */
class Solution {
    private int[][] memo;
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        memo[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            memo[i][0] = memo[i-1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            memo[0][j] = memo[0][j-1] + grid[0][j];
        }

        return dp(grid, m-1, n-1);
    }

    private int dp(int[][] grid, int i, int j) {
        if (i < 0 || j < 0) return Integer.MAX_VALUE;
        if (memo[i][j] != -1) return memo[i][j];
        memo[i][j] = grid[i][j] + Math.min(dp(grid, i-1, j), dp(grid, i, j-1));
        return memo[i][j];
    }
}

public class Main {

}
