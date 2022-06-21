package com.jianzhioffer.chapter4.jianzhi38;

import java.util.*;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 *
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 *
 * 示例:
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 *
 * 限制：
 *
 * 1 <= s 的长度 <= 8
 *
 * */

class Solution {

    // 结果集
    private List<String> res;

    // 字符串构造
    private StringBuilder temp;

    // 访问数组
    private boolean[] isVisited;

    // 初始字符串
    private String s;

    private void permutationCore(){
        if (temp.length() == s.length()){
            res.add(temp.toString());
            return;
        }

        HashSet<Character> set = new HashSet<>(); // 本层使用过的字母
        for (int i = 0; i < s.length(); i++) {
            // 本层使用的字母不能再使用 && 处于已访问的元素不可访问
            if (!set.contains(s.charAt(i)) && !isVisited[i]){
                // 访问
                isVisited[i] = true;
                // 添加当前字符
                temp.append(s.charAt(i));
                // 本层使用过
                set.add(s.charAt(i));
                // 下一层
                permutationCore();
                // 解除访问
                isVisited[i] = false;
                // 删除当前字符
                temp.deleteCharAt(temp.length()-1);
            }
        }

    }

    public String[] permutation(String s) {
        res = new ArrayList<>();
        temp = new StringBuilder();
        isVisited = new boolean[s.length()];
        this.s = s;
        permutationCore();
        return res.toArray(new String[0]);
    }

    private char[] string;
    void swap(int a, int b){
        char t = string[a];
        string[a] = string[b];
        string[b] = t;
    }

    // 从p到n的排列
    void dfs(int p){
        if (p == string.length - 1){
            res.add(new String(string));
            return;
        }

        // 本层使用过的字母，用于去重
        HashSet<Character> set = new HashSet<>();
        // i指向整个字符串的第一个字符
        // p指向执行排列操作的字符串的第一个字符
        for (int i = p; i < string.length; i++){
            if (!set.contains(string[i])){
                set.add(string[i]);
                // 求可能出现在第一个位置的字符，就是把第一个字符和后面所有字符交换
                swap(i,p);
                // 固定第一个字符，求后面字符串的排列
                // 从p+1到n的排列
                dfs(p+1);
                swap(i,p);
            }
        }
    }
    // 更高效
    public String[] permutation2(String s) {
        res = new ArrayList<>();
        string = s.toCharArray();
        // 从0到n的排列
        dfs(0);
        return res.toArray(new String[0]);
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] res = solution.permutation("baa");
        System.out.println(Arrays.toString(res));
    }
}
