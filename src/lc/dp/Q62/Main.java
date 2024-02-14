package lc.dp.Q62;

import java.util.Arrays;

/**
 * @Author Tsong
 * @Date 2023/9/15 21:42
 */

class Solution {
    private int[][] memo;
    public int uniquePaths(int m, int n) {
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < m; i++) {
            memo[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            memo[0][j] = 1;
        }
        return dp(m-1,n-1);
    }

    private int dp(int i, int j) {
        if (i < 0 || j < 0) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        int subProblem = dp(i - 1, j) + dp(i, j - 1);
        memo[i][j] = subProblem;
        return subProblem;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.uniquePaths(1,1));
    }
}
