package lc.daily.date2209.Interview1Q2;

import java.util.Arrays;

/**
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 *
 * 示例 1：
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 *
 * 示例 2：
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 *
 * 说明：
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 *
 * */
class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        char[] S1 = s1.toCharArray(), S2 = s2.toCharArray();
        Arrays.sort(S1);
        Arrays.sort(S2);
        s1 = String.valueOf(S1);
        s2 = String.valueOf(S2);
        return s1.equals(s2);
    }
}
public class Main {
}
