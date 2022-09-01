package lc.hot100.Q1to25.Q22;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 *
 * 提示：
 *
 * 1 <= n <= 8
 *
 * */

class Solution {
    private List<String> res;
    private StringBuilder temp;
    private int n;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        temp = new StringBuilder();
        this.n = n;
        // 跟踪左右括号的数量来判断括号组合是否合法
        dfs(0,0);
        return res;
    }
    private void dfs(int open, int close){
        if (temp.length() == 2 * n){
            res.add(temp.toString());
            return;
        }
        // 左括号数小于n
        if (open < n){
            temp.append('(');
            dfs(open+1,close);
            temp.deleteCharAt(temp.length()-1);
        }
        // 右括号数小于左括号数
        if (close < open){
            temp.append(')');
            dfs(open,close+1);
            temp.deleteCharAt(temp.length()-1);
        }
    }


}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.generateParenthesis(3));
    }
}
