package lc.labuladong.DynamicProgress.game.Q10;

class Solution {
    private String s, p;
    private int m, n;
    private int[][] memo;
    public boolean isMatch(String s, String p) {
        this.s = s;
        this.p = p;
        m = s.length();
        n = p.length();
        memo = new int[m][n];
        return dp(0,0);
    }

    // 定义：dp(i, j)为s[i..]与p[j..]的匹配情况，匹配为true，否则为false
    private boolean dp(int i, int j){
        // base case
        if (j == n) return i == m; // p结束了，如果s没结束说明不匹配
        // 需要使得p继续匹配空字符
        if (i == m){
            // 检查p剩下的字符是否成对出现
            if ((n - j) % 2 != 0){
                return false;
            }
            // 如果成对出现，是否是x*y*z*的形式
            for (; j + 1 < n; j+=2) {
                if (p.charAt(j+1) != '*') return false;
            }
            return true;
        }
        // 消除重叠子问题
        if (memo[i][j] != 0) return memo[i][j] == 1;

        boolean res = false;
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){
            if (j + 1 < n && p.charAt(j+1) == '*'){
                res = dp(i+1, j) || dp(i, j+2);
            } else {
                res = dp(i+1, j+1);
            }
        } else {
            if (j + 1 < n && p.charAt(j+1) == '*'){
                res = dp(i, j+2);
            } else {
                res = false;
            }
        }
        memo[i][j] = res ? 1 : 2;
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aa", p = "a";
        System.out.println(solution.isMatch(s, p));
    }
}
