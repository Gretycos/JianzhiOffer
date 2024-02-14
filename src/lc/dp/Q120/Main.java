package lc.dp.Q120;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Tsong
 * @Date 2023/9/16 18:58
 */
class Solution {
    private List<List<Integer>> triangle;

    private int[][] memo;
    public int minimumTotal(List<List<Integer>> triangle) {
        this.triangle = triangle;
        int m = triangle.size();
        memo = new int[m][];
        for (int i = 0; i < m; i++) {
            memo[i] = new int[i+1];
            Arrays.fill(memo[i], Integer.MIN_VALUE);
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < triangle.get(m-1).size(); j++) {
            min = Math.min(min, dp(m-1, j));
        }
        return min;
    }

    // dp(i,j) 从(0,0)到(i,j)的最小路径和
    private int dp(int i, int j) {
        if (i == 0) return triangle.get(0).get(0);
        if (j < 0 || j == triangle.get(i).size()) return Integer.MAX_VALUE;
        if (memo[i][j] != Integer.MIN_VALUE) return memo[i][j];
        int min = Math.min(dp(i - 1, j), dp(i - 1, j - 1)) + triangle.get(i).get(j);
        memo[i][j] = min;
        return memo[i][j];
    }
}
public class Main {
}
