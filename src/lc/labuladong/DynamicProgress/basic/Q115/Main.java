package lc.labuladong.DynamicProgress.basic.Q115;

import java.util.Arrays;

class Solution {
    private String s, t;
    private int[][] memo;
    public int numDistinct(String s, String t) {
        this.s = s;
        this.t = t;
        memo = new int[s.length()][t.length()];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(0,0);
    }

    // 定义dp(i,j)为 s[i..]子序列中出现t[j..]的次数
    private int dp(int i, int j){
        // 匹配完t了
        if (j == t.length()){
            return 1;
        }
        // s剩下的比t剩下的要短
        if (s.length() - i < t.length() - j){
            return 0;
        }
        if (memo[i][j] != -1){
            return memo[i][j];
        }
        int res = 0;
        if (s.charAt(i) == t.charAt(j)){
            res += dp(i+1, j+1) + dp(i+1, j);
        } else {
            res += dp(i+1, j);
        }
        memo[i][j] = res;
        return res;
    }
}
public class Main {
}
