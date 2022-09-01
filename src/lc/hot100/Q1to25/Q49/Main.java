package lc.hot100.Q1to25.Q49;

import java.util.*;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *
 *
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i]仅包含小写字母
 */

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String s: strs){
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i)-'a']++;
            }
            StringBuilder keyBuilder = new StringBuilder();
            for (int i = 0; i < count.length; i++) {
                while(count[i] != 0){
                    keyBuilder.append('a'+i);
                    count[i] --;
                }
            }
            String key = keyBuilder.toString();
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String s: strs){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(solution.groupAnagrams(strs));
    }
}
