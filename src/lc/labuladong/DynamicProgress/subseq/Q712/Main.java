package lc.labuladong.DynamicProgress.subseq.Q712;

import java.util.Arrays;

class Solution{
    private int[][] memo;
    private String s1, s2;
    public int minimumDeleteSum(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        int m = s1.length(), n = s2.length();
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(0,0);
    }

    // 定义：将 s1[i..] 和 s2[j..] 删除成相同字符串，
    // 最小的 ASCII 码之和为 dp(i, j)。
    private int dp(int i, int j){
        int res = 0;
        // base case
        if (i == s1.length()){
            // 如果 s1 到头了，那么 s2 剩下的都得删除
            while (j < s2.length()){
                res += s2.charAt(j++);
            }
            return res;
        }
        if (j == s2.length()){
            // 如果 s2 到头了，那么 s1 剩下的都得删除
            while (i < s1.length()){
                res += s1.charAt(i++);
            }
            return res;
        }

        // 备忘录
        if (memo[i][j] != -1){
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)){
            // s1[i] 和 s2[j] 都是在 lcs 中的，不用删除
            memo[i][j] = dp(i+1, j+1);
        } else {
            // s1[i] 和 s2[j] 至少有一个不在 lcs 中，删一个
            memo[i][j] = Math.min(
                    s1.charAt(i) + dp(i+1, j), // s[i]不在lcs中
                    s2.charAt(j) + dp(i, j+1)  // s[j]不在lcs中
            );
        }
        return memo[i][j];
    }
}
public class Main {
}
