package com.algorithm.chapter4.jianzhi38;

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

    private List<String> res;

    private char[] temp;

    private boolean[] isVisited;

    private void permutationCore(String s, int p){
        if (p == s.length()){
            res.add(new String(temp));
            return;
        }

        HashSet<Character> set = new HashSet<>(); // 本层使用过的字母
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))){ // 已经使用过了，就跳过，否则会出现重复
                continue;
            }
            if (!isVisited[i]){
                isVisited[i] = true;
                temp[p] = s.charAt(i);
                permutationCore(s,p+1);
                isVisited[i] = false;
                set.add(s.charAt(i));
            }
        }
    }

    public String[] permutation(String s) {
        res = new ArrayList<>();
        temp = new char[s.length()];
        isVisited = new boolean[s.length()];
        permutationCore(s,0);
        return res.toArray(new String[0]);
    }

    void swap(int a, int b){
        char t = temp[a];
        temp[a] = temp[b];
        temp[b] = t;
    }
    void dfs(int p){
        if (p == temp.length - 1){
            res.add(new String(temp));
            return;
        }

        HashSet<Character> set = new HashSet<>();
        for (int i = p; i < temp.length; i++){
            if (set.contains(temp[i])){
                continue;
            }
            set.add(temp[i]);
            swap(i,p);
            dfs(p+1);
            swap(i,p);
        }
    }
    public String[] permutation2(String s) {
        res = new ArrayList<>();
        temp = s.toCharArray();
        dfs(0);
        return res.toArray(new String[0]);
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] res = solution.permutation2("baa");
    }
}
