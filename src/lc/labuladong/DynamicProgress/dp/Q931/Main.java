package lc.labuladong.DynamicProgress.dp.Q931;

import java.util.Arrays;

class Solution {
    private int[][] memo;
    private int[][] matrix;
    public int minFallingPathSum(int[][] matrix) {
        this.matrix = matrix;
        int n = matrix.length;
        int res = Integer.MAX_VALUE;
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], 66666);
        }
        // 终点可能在matrix[n-1]的任意一列
        for (int j = 0; j < n; j++) {
            res = Math.min(res, dp(n-1, j));
        }
        return res;
    }

    private int dp(int i, int j){
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length){
            return 99999;
        }
        // base case
        if (i == 0){
            return matrix[0][j];
        }
        if (memo[i][j] != 66666){
            return memo[i][j];
        }
        memo[i][j] = matrix[i][j] + Math.min(
                dp(i-1,j),Math.min(
                        dp(i-1, j-1), dp(i-1, j+1)));
        return memo[i][j];
    }
}

public class Main {
}
