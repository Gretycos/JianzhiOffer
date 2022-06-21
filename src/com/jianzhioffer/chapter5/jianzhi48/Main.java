package com.jianzhioffer.chapter5.jianzhi48;

import java.util.HashMap;
import java.util.Map;

/**
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 *
 *
 * 示例1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *     请注意，你的答案必须是 子串 的长度， "pwke"是一个子序列，不是子串。
 *
 *
 * 提示：
 *
 * s.length <= 40000
 *
 * */


class Solution {
    // 滑动窗口
    public int lengthOfLongestSubstring2(String s){
        //哈希表统计字符s[j] 最后一次出现的索引
        Map<Character, Integer> map = new HashMap<>();
        int i = -1, // 左指针，根据上轮的左指针和重复字符下标，保证区间[i+1,j]内无重复字符且最大
                max = 0; // 维护最大长度，取上轮长度与本轮双指针区间的长度的最大值
        // 遍历字符串
        for(int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if(map.get(c) != null)
                i = Math.max(i, map.get(c)); // 更新左指针 i
            map.put(c, j); // 哈希表记录
            max = Math.max(max, j - i); // 更新结果
        }
        return max;
    }


    // 动态规划
    public int lengthOfLongestSubstring(String s) {
        int curLength = 0; // 以c结尾的不重复的字符串的长度
        int maxLength = 0;
        // <字母,在s中的下标>
        Map<Character,Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int prevIndex = map.get(c) == null? -1 : map.get(c);
            // 不存在就加入当前字符串
            // 如果d > curLength，说明c不在当前字符串中，因此当前字符也可以加入候选，长度+1
            // dp[i] = dp[i-1] + 1;
            if (prevIndex < 0 || i - prevIndex > curLength){
                curLength ++;
            } else {
                // 更新最大值
                maxLength = Math.max(maxLength,curLength);
                // 如果d < curLength 说明c已经存在当前字符串中，因此要重新计算以c结尾的字符串长度
                // dp[i] = i - prev;
                curLength = i - prevIndex;
            }
            map.put(c,i);
        }

        // 更新最大值
        if (curLength > maxLength){
            maxLength = curLength;
        }

        return maxLength;
    }


    // 暴力
    public int lengthOfLongestSubstring0(String s) {
        // <字母，在字符串中的下标>
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        Map<Character,Integer> current = map1;
        Map<Character,Integer> next = map2;
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // current没有
            if (current.get(c) == null){
                current.put(c,i);
                max = Math.max(max,current.size());
            } else { // current有重复字符
                next.clear();
                int start = current.get(c)+1;
                for (int j = start; j <= i; j++) {
                    next.put(s.charAt(j),j);
                }
                max = Math.max(max,next.size());
                Map t = current;
                current = next;
                next = t;
            }
        }
        return max;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "alouzxilkaxkufsu";
        System.out.println(solution.lengthOfLongestSubstring(s));
    }
}
