package lc.offer2.chapter5.jianzhi50;

import java.util.*;

/**
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 *
 * 示例 1:
 *
 * 输入：s = "abaccdeff"
 * 输出：'b'
 * 示例 2:
 *
 * 输入：s = ""
 * 输出：' '
 *
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 50000
 *
 * */

class Solution {

    public char firstUniqChar2(String s) {
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)-'a'] ++;
        }
        // 遍历字符串的字母对应的次数，第一个1满足条件
        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i)-'a'] == 1)
                return s.charAt(i);
        }
        return ' ';
    }


    public char firstUniqChar(String s) {
        Map<Character,Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) == null){
                map.put(c,i);
            }else{
                map.put(c,-1);
            }
        }
        char res = ' ';
        int min = Integer.MAX_VALUE;
        for (Integer value : map.values()) {
            if (value != -1){
                min = Math.min(min, value);
            }
        }
        if (min != Integer.MAX_VALUE){
            res = s.charAt(min);
        }
        return res;
    }
}


public class Main {
}
