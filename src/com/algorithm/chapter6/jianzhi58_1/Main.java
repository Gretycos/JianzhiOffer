package com.algorithm.chapter6.jianzhi58_1;


/**
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 * 为简单起见，标点符号和普通字母一样处理。
 * 例如输入字符串"I am a student. "，则输出"student. a am I"。
 *
 *
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 *
 * 输入: " hello world! "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *
 * 示例 3：
 *
 * 输入: "a good  example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 *
 * 说明：
 *
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * */

class Solution {
    public String reverseWords(String s) {
        String[] words = s.strip().split(" ");
        StringBuffer res = new StringBuffer();
        for (int i = words.length-1; i >= 0 ; i--) {
            if (!words[i].equals(" ") && !words[i].equals("")) {
                res.append(words[i]);
                if (i != 0){
                    res.append(" ");
                }
            }
        }
//        int length = res.length();
//        if (length > 0 && res.charAt(length - 1) == ' '){
//            res.deleteCharAt(length - 1);
//        }

        return res.toString();
    }

    // 底层方法
    public String reverseWords1(String s) {
        if (s.length() == 0) return s;
        StringBuilder sb = new StringBuilder(s);
        removeBlank(sb);
        reverse(sb,0,sb.length()-1);
        reverseSingleWord(sb);
        return sb.toString();
    }

    private void removeBlank(StringBuilder sb){
        int i = 0;
        while (i < sb.length() && sb.charAt(i)==' '){
            sb.deleteCharAt(i);
        }
        int j = sb.length() - 1;
        while(j >= 0 && sb.charAt(j) == ' '){
            sb.deleteCharAt(j--);
        }
    }

    private void reverse(StringBuilder sb, int start, int end){
        for (int i = start, j = end; i <= j; i++,j--) {
            char c = sb.charAt(i);
            sb.setCharAt(i,sb.charAt(j));
            sb.setCharAt(j,c);
        }
    }

    private void reverseSingleWord(StringBuilder sb){
        int i = 0, j = 0;
        while(i < sb.length()){
            if (sb.charAt(i) == ' '){
                sb.deleteCharAt(i);
            } else if (j == sb.length() || sb.charAt(j) == ' ') {
                reverse(sb,i,j-1);
                i = ++j;
            }else{
                j++;
            }
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = " a good  example! ";
        System.out.println(solution.reverseWords(s));
    }
}
