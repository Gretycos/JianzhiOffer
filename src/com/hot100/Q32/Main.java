package com.hot100.Q32;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给你一个只包含 '('和 ')'的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 *
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 *
 * 输入：s = ""
 * 输出：0
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 *
 * */

class Solution {
    public int longestValidParentheses(String s) {
        int max = 0;
        // 栈内保存最后一个不满足条件的右括号或者-1作为边界
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(-1);
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '('){ // 左括号直接压入
                stack.addLast(i);
            }else{ // 遍历到右括号
                // 弹出一个右括号或左括号
                // 弹出左括号表示匹配，弹出右括号表示替换
                stack.removeLast();
                // 匹配/替换之后栈为空，把当前这个右括号作为边界压入
                if (stack.isEmpty()){
                    stack.addLast(i);
                }else{
                    max = Math.max(max,i-stack.getLast());
                }
            }
        }
        return max;
    }
    public int longestValidParentheses2(String s) {
        char[] chars = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '('){
                stack.addLast(i);
            }else{
                if (stack.isEmpty()){
                    chars[i] = '1';
                }else{
                    stack.removeLast();
                }
            }
        }

        while(!stack.isEmpty()){
            chars[stack.removeLast()] = '1';
        }

        int max = 0, current = 0;
        for (char c : chars) {
            if (c == '1'){
                current = 0;
                continue;
            }
            max = Math.max(max,++current);
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "(()";
        System.out.println(solution.longestValidParentheses2(s));
    }
}
