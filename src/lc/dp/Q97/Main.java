package lc.dp.Q97;

import java.util.Arrays;

/**
 * @Author Tsong
 * @Date 2023/9/16 17:46
 */
class Solution {
    private String s1, s2, s3;
    private int[][] memo;
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) return false;
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        return dp(m-1, n-1);
    }

    private boolean dp(int i, int j) {
        if (i < 0 || j < 0) {
            if (i < 0 && j < 0) return true;
            if (i < 0) {
                return s2.charAt(j) == s3.charAt(j) && dp(i, j-1);
            } else {
                return s1.charAt(i) == s3.charAt(i) && dp(i-1, j);
            }
        }
        if (memo[i][j] != -1) return memo[i][j] == 1;

        boolean res = (s1.charAt(i) == s3.charAt(i+j+1) && dp(i-1, j))
                || (s2.charAt(j) == s3.charAt(i+j+1) && dp(i, j-1));

        memo[i][j] = res ? 1 : 0;
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "db", s2 = "b", s3 = "cbb";
        System.out.println(solution.isInterleave(s1,s2,s3));
    }
}
