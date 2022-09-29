package lc.hot100.Q76toQ100.Q301;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 *
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 *
 *
 * 示例 1：
 *
 * 输入：s = "()())()"
 * 输出：["(())()","()()()"]
 *
 *
 * 示例 2：
 *
 * 输入：s = "(a)())()"
 * 输出：["(a())()","(a)()()"]
 *
 *
 * 示例 3：
 *
 * 输入：s = ")("
 * 输出：[""]
 *
 * 提示：
 *
 * 1 <= s.length <= 25
 * s 由小写英文字母以及括号 '(' 和 ')' 组成
 * s 中至多含 20 个括号
 *
 * */

class Solution {
    private List<String> res;

    private int invalidLeft, invalidRight;
    public List<String> removeInvalidParentheses(String s) {
        res = new ArrayList<>();
        calInvalid(s);  // 计算最少需要移除的左括号数和右括号数
        dfs(s,0,invalidLeft,invalidRight);
        return res;
    }

    private void calInvalid(String s){
        invalidLeft = invalidRight = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '('){
                invalidLeft ++;
            }else if (c == ')'){
                if (invalidLeft == 0){
                    invalidRight++;
                }else{
                    invalidLeft--;
                }
            }
        }
    }

    private void dfs(String s, int start, int invalidL, int invalidR){
        if (invalidL == 0 && invalidR == 0){
            if (isValid(s)){ // 组合是否合法
                res.add(s);
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {
            char current = s.charAt(i);
            // 连续相同就跳过，因为删减效果一样
            if (i > start && current == s.charAt(i-1)){
                continue;
            }
            // 如果剩余字符串长度不足，直接返回
            if (s.length() - i < invalidL + invalidR){
                return;
            }

            if ((invalidL > 0 && current == '(') || (invalidR > 0 && current == ')')){
                // 删除i位置的字符构建新串
                String newString = s.substring(0,i) + s.substring(i+1);
                // 删除一个左括号
                if (invalidL > 0 && current == '('){
                    dfs(newString,i,invalidL-1,invalidR);
                }
                // 删除一个右括号
                if (invalidR > 0 && current == ')'){
                    dfs(newString,i,invalidL,invalidR-1);
                }
            }
        }
    }

    private boolean isValid(String s){
        int rest = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '('){
                rest++;
            }else if (c == ')'){
                if (--rest < 0){
                    return false;
                }
            }
        }
        return rest == 0;
    }
}
public class Main {
}
