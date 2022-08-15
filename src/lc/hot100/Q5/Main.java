package lc.hot100.Q5;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 *
 * */

class Solution {

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    // 中心扩展
    // 时间O(n^2) 空间O(n)
    // 最差情况下扩张到两侧，扩张长度O(n)
    // 尽管时间O(n^2)，但是实际运行时间比dp少一个数量级
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    // dp
    // 时间O(n^2) 空间O(n^2)
    public String longestPalindrome2(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0, end = 0;
        for (int i = n-1; i >=0 ; i--) {
            for (int j = i; j < n; j++) {
                // 字符相等
                if (s.charAt(i) == s.charAt(j)){
                    // 下标小于3
                    if (j - i < 3){
                        dp[i][j] = true;
                    } else {
                        if (i + 1 < n && j - 1 >= 0){
                            // 判断s[i+1~j-1]是否是回文
                            dp[i][j] = dp[i+1][j-1];
                        }
                    }
                }
                if (dp[i][j]){
                    int len = j - i + 1;
                    if (len > end - start){
                        start = i;
                        end = j;
                    }
                }
            }
        }
        return s.substring(start,end+1);
    }
}


public class Main {

}
