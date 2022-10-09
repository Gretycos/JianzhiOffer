package lc.daily.date2210.Q856;

/**
 * 给定一个平衡括号字符串S，按下述规则计算该字符串的分数：
 *
 * () 得 1 分。
 * AB 得A + B分，其中 A 和 B 是平衡括号字符串。
 * (A) 得2 * A分，其中 A 是平衡括号字符串。
 *
 *
 * 示例 1：
 * 输入： "()"
 * 输出： 1
 *
 *
 * 示例 2：
 * 输入： "(())"
 * 输出： 2
 *
 *
 * 示例3：
 * 输入： "()()"
 * 输出： 2
 *
 *
 * 示例4：
 * 输入： "(()(()))"
 * 输出： 6
 *
 *
 * 提示：
 * S是平衡括号字符串，且只含有(和)。
 * 2 <= S.length <= 50
 * */

class Solution {
    private int p,n;
    private char[] s;
    public int scoreOfParentheses(String s) {
        this.s = s.toCharArray();
        p = 0;
        n = s.length();
        return dfs();
    }

    private int dfs(){
        int point = 0;
        while (p < n && s[p] == '('){
            p ++; // 跳过 (
            if (s[p] == ')'){ // 遇到右括号构成()
                point ++;
            } else { // 还是左括号，递归进入下一层
                point += 2 * dfs();
            }
            p ++; // 跳过 )
        }
        return point;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "(()((())))";
        System.out.println(solution.scoreOfParentheses(s));
    }
}
