package lc.labuladong.DataStructure.dp.Q72;

import java.util.Arrays;

class Solution {
    private String s1, s2;
    private int[][] memo;
    // memo 方式
    public int minDistance(String word1, String word2) {
        s1 = word1;
        s2 = word2;
        int m = s1.length(), n = s2.length();
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row,-1);
        }
        // 从字符串尾部开始遍历
        return dp(m-1,n-1);
    }

    // 定义：dp(i,j)表示s1[i]->s2[j]的最少操作数
    private int dp(int i, int j){
        // 如果s1走完了，返回s2剩余长度
        if (i == -1) return j + 1;
        // 如果s2走完了，返回s1剩余长度
        if (j == -1) return i + 1;
        // 备忘录，消除重叠子问题
        if (memo[i][j] != -1) return memo[i][j];

        if (s1.charAt(i) == s2.charAt(j)){
            memo[i][j] = dp(i-1, j-1); // 跳过
        } else {
            memo[i][j] = min(
                    dp(i,j-1) + 1, // 插入，在s1[i]插入，所以j要往前移动
                    dp(i-1,j) + 1,  // 删除，删除s1[i]，i要往前移动
                    dp(i-1,j-1) + 1 // 替换
            );
        }
        return memo[i][j];
    }

    private int min(int a, int b, int c){
        return Math.min(a,Math.min(b,c));
    }

    // dp table
    public int minDistance2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // 定义：s1[0..i]和s2[0..j]的最小编辑距离是dp[i+1][j+1]
        int[][] dp = new int[m+1][n+1];
        // base case
        // 处理空串的情况
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        // 自底向上
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 有索引偏移
                if (word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = min(
                            dp[i][j-1] + 1, // 插入s1[i]
                            dp[i-1][j] + 1, // 删除s1[i]
                            dp[i-1][j-1] + 1 // 替换
                    );
                }
            }
        }
        return dp[m][n];
    }
}
public class Main {
}
