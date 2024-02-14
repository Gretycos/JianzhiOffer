package lc.dp.Q5;

/**
 * @Author Tsong
 * @Date 2023/9/9 15:38
 */
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int maxLength = 0, start = 0, end = 0;
        for (int i = 0; i < s.length(); i++){
            int l1, l2 = 0;
            l1 = findLongestLength(s, i-1, i+1) + 1;
            if (i < s.length() - 1 && s.charAt(i) == s.charAt(i+1)){
                l2 = findLongestLength(s, i-1, i + 2) + 2;
            }
            int max = Math.max(l1, l2);
            if (max > maxLength) {
                maxLength = max;
                if (l1 == max) {
                    start = i - l1 / 2;
                    end = i + l1 / 2;
                } else if (l2 == max) {
                    start = i - l2 / 2  + 1;
                    end = i + l2 / 2;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    private int findLongestLength(String s, int left, int right){
        int length = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left --;
            right ++;
            length += 2;
        }
        return length;
    }

    public String longestPalindrome_dp(String s) {
        if (s == null || s.length() == 0) return "";
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int maxLength = 0, start = 0, end = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)){
                    if (j - i < 3){
                        dp[i][j] = true;
                    } else {
                        if (i + 1 < n && j - 1 > i){
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    }
                    if (dp[i][j]){
                        int length = j - i + 1;
                        if (length > maxLength){
                            maxLength = length;
                            start = i;
                            end = j;
                        }
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aaaa";
        System.out.println(solution.longestPalindrome_dp(s));
    }
}
