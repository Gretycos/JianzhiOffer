package lc.dp.Q63;

import java.util.Arrays;

/**
 * @Author Tsong
 * @Date 2023/9/15 21:56
 */
class Solution {
    private int[][] memo;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int i = 0;
        while (i < m && obstacleGrid[i][0] != 1) {
            memo[i++][0] = 1;
        }
        while (i < m) {
            memo[i++][0] = 0;
        }

        int j = 0;
        while (j < n && obstacleGrid[0][j] != 1) {
            memo[0][j++] = 1;
        }
        while (j < n) {
            memo[0][j++] = 0;
        }

        return dp(obstacleGrid, m-1, n-1);
    }

    private int dp(int[][] obstacleGrid, int i, int j) {
        if (i < 0 || j < 0) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        memo[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp(obstacleGrid, i - 1, j) + dp(obstacleGrid, i, j - 1);
        return memo[i][j];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] obstacleGrid = {
                {0,0,1,0},
                {0,0,0,0},
                {0,1,0,0}
        };
        System.out.println(solution.uniquePathsWithObstacles(obstacleGrid));
    }
}
