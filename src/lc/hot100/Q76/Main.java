package lc.hot100.Q76;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 *
 *
 * 注意：
 *
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3:
 *
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 10^5
 * s 和 t 由英文字母组成
 *
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 *
 * */

class Solution {
    public String minWindow(String s, String t) {
        Map<Character,Integer> targetMap = new HashMap<>();
        Map<Character,Integer> currentMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            targetMap.put(c,targetMap.getOrDefault(c,0)+1);
        }
        int count = 0, length = Integer.MAX_VALUE;
        int start = 0;
        for (int r = 0, l = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            currentMap.put(c,currentMap.getOrDefault(c,0)+1);
            // 字符合法数计算
            if (targetMap.containsKey(c) && currentMap.get(c) <= targetMap.get(c)){
                count ++;
            }
            // targetMap里没有左指针的字符或者currentMap里的左指针字符数量大于targetMap里的
            // 从currentMap中去除这个字符
            // 缩小窗口 l++
            while(l < r &&
                    (!targetMap.containsKey(s.charAt(l)) || currentMap.get(s.charAt(l)) > targetMap.get(s.charAt(l)))){
                currentMap.put(s.charAt(l),currentMap.get(s.charAt(l)) - 1);
                l++;
            }
            if (count == t.length() && r - l + 1 < length){
                length = r - l + 1;
                start = l;
            }
        }
        return length == Integer.MAX_VALUE ? "" : s.substring(start,start+length);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(solution.minWindow(s,t));
    }
}
