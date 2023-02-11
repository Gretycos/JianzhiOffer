package lc.labuladong.DynamicProgress.subseq.Q1143;

import java.util.Arrays;

class Solution {
    private int[][] memo;
    private String s1, s2;
    // memo
    public int longestCommonSubsequence(String text1, String text2) {
        s1 = text1;
        s2 = text2;
        int m = s1.length(), n = s2.length();
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(0,0);
    }
    // 定义：计算s1[i..]和s2[j..]的最长公共子序列长度
    private int dp(int i, int j){
        // base case
        if (i == s1.length() || j == s2.length()){
            return 0;
        }
        // 备忘录
        if (memo[i][j] != -1){
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)){
            memo[i][j] = 1 + dp(i+1, j+1);
        } else {
            memo[i][j] = Math.max(
                    dp(i+1, j), // s[i]不在lcs中
                    dp(i, j+1)  // s[j]不在lcs中
                    // dp(i+1,j+1)  s[i]和s[j]都不在lcs中，
                    // 但是这种情况包含在了情况2，它不会比dp(i,j+1)更长
            );
        }
        return memo[i][j];
    }

    // dp table
    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        // 定义：s1[0..i-1] 和 s2[0..j-1] 的 lcs 长度为 dp[i][j]
        // 目标：s1[0..m-1] 和 s2[0..n-1] 的 lcs 长度，即 dp[m][n]
        // base case: dp[0][..] = dp[..][0] = 0
        int[][] dp = new int[m+1][n+1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[m][n];
    }
}


public class Main {
}
