package com.algorithm.chapter3.jianzhi19;

/**
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 *
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释:因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释:".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释:因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母以及字符.和*，无连续的 '*'。
 *
 * */

class Solution {

    // 动态规划
    private boolean matchDP(String s, String p){
        int m = s.length();
        int n = p.length();
        // 多开1个单位，因为有空字符或者空匹配的情况
        // dp定义：s的前i个字符与p的前j个字符是否能匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // 分成空正则和非空正则两种
                // 空正则
                if (j == 0) {
                    dp[i][j] = (i == 0);
                } else {
                    // 非空正则
                    // 非空正则分为两种情况 * 和 非*
                    // 非*
                    if (p.charAt(j - 1) != '*') {
                        // 匹配
                        // s当前字符 == p当前字符 || p当前字符是.
                        if (i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    } else {
                        // *
                        // *，分为(a*或.*)和b*两种情况
                        // a*或.*, 匹配1次/多次 或 不匹配
                        if (i >= 1 && j >= 2 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')) {
                            // 匹配了1次可以拿掉s末尾的a，然后用a*继续匹配
                            // 因为f[i][j]与f[i-1][j]有关，所以迭代下去相当于匹配了多次
                            dp[i][j] = dp[i - 1][j] ||
                                    dp[i][j - 2]; // 不匹配
                        }else if (j >= 2){
                            // b*
                            // 不匹配
                            dp[i][j] = dp[i][j - 2];
                        }
                    }
                }
            }
        }
        return dp[m][n];
    }

    // 递归 很慢
    private boolean match(String s, String p, int p1, int p2){
        // 两指针刚好结束
        if (p1 == s.length() && p2 == p.length()){
            return true;
        }

        // 模式结束了，字符串还未结束
        if (p1 < s.length() && p2 == p.length()){
            return false;
        }

        if (p2+1 < p.length()){
            // 模式下一位是*
            if (p.charAt(p2+1) == '*'){
                // a* || .*
                if (p1 < s.length() && (s.charAt(p1) == p.charAt(p2) || p.charAt(p2) == '.')){
                    return match(s,p,p1+1,p2+2) || // 匹配一次
                            match(s,p,p1+1,p2) || // 匹配多次
                            match(s,p,p1,p2+2); // 不匹配
                } else {
                    // b*
                    return match(s,p,p1,p2+2); // 不匹配
                }
            } else {
                // 模式下一位不是*
                if (p1 < s.length() && (s.charAt(p1) == p.charAt(p2) || p.charAt(p2) == '.')){
                    return match(s,p,p1+1,p2+1);
                }
            }
        }
        return false;
    }

    public boolean isMatch(String s, String p) {
        return matchDP(s,p);
//        return match(s,p,0,0);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aab";
        String p = "c*a*b";
        System.out.println(solution.isMatch(s,p));
    }
}
