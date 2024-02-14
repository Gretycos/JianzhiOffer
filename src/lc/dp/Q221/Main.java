package lc.dp.Q221;

import java.util.Arrays;

/**
 * @Author Tsong
 * @Date 2023/9/25 10:07
 */
class Solution {
    private int[][] memo;
    private char[][] matrix;
    private int maxSide;
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        this.matrix = matrix;
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        maxSide = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp(i, j);
            }
        }
        return maxSide * maxSide;
    }

    // dp(i,j)：右下角是(i,j)的正方形的边长
    private int dp(int i, int j) {
        if (i < 0 || j < 0) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        if (matrix[i][j] == '0') {
            memo[i][j] = 0;
        } else {
            // 关键，难想到
            memo[i][j] = Math.min(
                    dp(i - 1, j - 1),
                    Math.min(
                            dp(i, j - 1),
                            dp(i - 1, j)
                    )
            ) + 1;
        }
        maxSide = Math.max(maxSide, memo[i][j]);
        return memo[i][j];
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] matrix = {
                {'0'}
        };
        System.out.println(solution.maximalSquare(matrix));
    }
}
