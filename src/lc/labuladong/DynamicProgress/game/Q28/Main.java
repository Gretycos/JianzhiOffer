package lc.labuladong.DynamicProgress.game.Q28;

class Solution {
    private int[][] dp;
    private String pat;
    private void KMP(String pat){
        this.pat = pat;
        int M = pat.length();
        // dp[状态][字符] = 下个状态
        dp = new int[M][26];
        // base case
        dp[0][pat.charAt(0)-'a'] = 1;
        // 影子X=0
        int X = 0;
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < 26; c++) {
                if (pat.charAt(j)-'a' == c){
                    // 状态推进
                    dp[j][c] = j + 1;
                }else{
                    // 状态重启（上一次的结果）
                    dp[j][c] = dp[X][c];
                }
            }
            // 更新影子状态
            // 当前是状态 X，遇到字符 pat[j]，
            // pat 应该转移到哪个状态？
            X = dp[X][pat.charAt(j)-'a'];
        }
    }

    private int search(String txt){
        int M = pat.length();
        int N = txt.length();
        // pat初始态
        int j = 0;
        for (int i = 0; i < N; i++) {
            j = dp[j][txt.charAt(i) - 'a'];
            if (j == M) return i - M + 1;
        }
        return -1;
    }

    public int strStr(String haystack, String needle) {
        KMP(needle);
        return search(haystack);
    }
}

public class Main {
}
