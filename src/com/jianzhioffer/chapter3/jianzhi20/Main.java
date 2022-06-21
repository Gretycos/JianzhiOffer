package com.jianzhioffer.chapter3.jianzhi20;

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 *
 * 数值（按顺序）可以分成以下几个部分：
 *
 * 若干空格
 * 一个小数或者整数
 * （可选）一个'e'或'E'，后面跟着一个整数
 * 若干空格
 * 小数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分数值列举如下：
 *
 * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * 部分非数值列举如下：
 *
 * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 *
 *
 * 示例 1：
 *
 * 输入：s = "0"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "e"
 * 输出：false
 * 示例 3：
 *
 * 输入：s = "."
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "    .1  "
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
 *
 * */

class Solution {
    private int p = 0; // 字符串索引

    private String deleteSpace(String s){
        int i = 0;
        int j = s.length() - 1;
        while(i < s.length() && s.charAt(i) == ' '){
            i++;
        }
        while(j > i && s.charAt(j) == ' '){
            j--;
        }

        return j >= i ? s.substring(i,j+1) : null;
    }

    private boolean isInteger(String s){
        if (p < s.length() && (s.charAt(p) == '+' || s.charAt(p) == '-')){
            p++;
        }
        return isUnsignedInteger(s);
    }

    private boolean isUnsignedInteger(String s) {
        int p1 = p;
        while(p < s.length() && (s.charAt(p) >='0' && s.charAt(p) <= '9')){
            p++;
        }
        // 如果存在0-9的数字就返回true
        return p > p1;
    }

    public boolean isNumber(String s) {
        // 去掉空格
        s = deleteSpace(s);

        if (s == null) {
            return false;
        }

        // 先看第一部分是否是整数
        boolean numeric = isInteger(s);

        // 遇到.之后判断后续是否是无符号数
        // 用||的原因
        // 小数可以是123. 或 .456 或 123.456
        if (p < s.length() && s.charAt(p) == '.'){
            p++;
            numeric = isUnsignedInteger(s) || numeric;
        }

        // 遇到e/E之后判断后续指数部分是否为整数
        // 用&&的原因
        // e/E前面如果不是数字，则后续无意义
        if (p < s.length() && (s.charAt(p) == 'e' || s.charAt(p) == 'E')){
            p++;
            numeric = numeric && isInteger(s);
        }

        // 指针刚好指向最后一个字符的下一位
        return numeric && p == s.length();
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "0.8";
        System.out.println(solution.isNumber(s));
    }
}
