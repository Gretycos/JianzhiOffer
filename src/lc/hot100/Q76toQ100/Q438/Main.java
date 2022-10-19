package lc.hot100.Q76toQ100.Q438;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 *
 *
 * 示例1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 *
 * 示例 2:
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 *
 * 提示:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s和p仅包含小写字母
 *
 * */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] dict = new int[26]; // p的字典
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            dict[c-'a'] ++;
        }
        int[] curDict = new int[26]; // 记录扫描过程中在p中存在的字符及其个数
        int size = 0; // 满足条件的个数
        List<Integer> res = new ArrayList<>();

        // 滑动窗口
        int l = 0, r = 0;
        while(r < s.length()){
            char c = s.charAt(r);
            r++;
            if (dict[c-'a'] == 0){ // 字典中没有这个字符
                l = r;
                curDict = new int[26];
                size = 0;
            }else{ // 字典有这个字符
                curDict[c-'a']++;
                size ++;
                // l++缩小窗口，并把对应字符的个数-1
                while (curDict[c-'a'] > dict[c-'a']){
                    char c_del = s.charAt(l);
                    l++;
                    curDict[c_del-'a']--;
                    size--;
                }
                // 满足条件的个数与p的长度一致，把l加入结果集
                if (size == p.length()){
                    res.add(l);
                }
            }
        }
        return res;
    }
}


public class Main {
}
