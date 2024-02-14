package lc.dp.Q91;

import java.util.Arrays;

/**
 * @Author Tsong
 * @Date 2023/9/15 22:52
 */
class Solution {
    private int[] memo;
    public int numDecodings(String s) {
        memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return dp(s, 0);
    }

    // dp(i): s[0..n-1]的编码数
    private int dp(String s, int i) {
        // 超界返回1，因为当s='10'时，dp(0) = dp(1) + dp(2)
        if (i >= s.length()) return 1;
        // base case
        if (s.charAt(i) == '0') return 0;
        // 备忘录
        if (memo[i] != -1) return memo[i];

        // 如果没有26的限制的话
        // dp(i) = dp(i+1) + dp(i+2)
        // 但是现在有限制，所以dp(i+2)要在出现10~26的时候才能加入结果
        memo[i] = dp(s, i+1);
        if (i + 1 < s.length()) {
            int t = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
            if (t >= 10 && t <= 26) {
                memo[i] += dp(s, i+2) ;
            }
        }
        return memo[i];
    }
}
public class Main {
}
