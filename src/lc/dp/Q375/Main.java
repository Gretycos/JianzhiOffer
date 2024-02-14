package lc.dp.Q375;

import java.util.Arrays;

/**
 * @Author Tsong
 * @Date 2024/2/14 11:02
 */

class Solution {
    private int[][] memo;
    public int getMoneyAmount(int n) {
        memo = new int[n+1][n+1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(1, n);
    }

    private int dp(int i, int j){
        if (i == j) return 0;
        if (i + 1 == j) return i;
        if (memo[i][j] != -1) return memo[i][j];

        int res = Integer.MAX_VALUE;
        for (int k = i+1; k < j; k++) {
            // 求不同策略的最大值 最差情况
            int curMax = Math.max(dp(i,k-1), dp(k+1, j)) + k;
            // 求所有最大值的最小值
            res = Math.min(res, curMax);
        }
        memo[i][j] = res;
        return memo[i][j];
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getMoneyAmount(10));
    }
}
