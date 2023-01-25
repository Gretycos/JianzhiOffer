package lc.labuladong.DynamicProgress.dp.Q583;

class Solution {
    public int minDistance(String word1, String word2) {
        int lcs = longestCommonSubsequence(word1,word2);
        return word1.length() - lcs + word2.length() - lcs;
    }
    private int longestCommonSubsequence(String text1, String text2) {
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
