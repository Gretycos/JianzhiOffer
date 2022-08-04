package com.daily.Q899;

import java.util.Arrays;

/**
 * 给定一个字符串 s 和一个整数 k。你可以从 s 的前 k 个字母中选择一个，并把它加到字符串的末尾。
 *
 * 返回 在应用上述步骤的任意数量的移动后，字典上最小的字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "cba", k = 1
 * 输出："acb"
 * 解释：
 * 在第一步中，我们将第一个字符（“c”）移动到最后，获得字符串 “bac”。
 * 在第二步中，我们将第一个字符（“b”）移动到最后，获得最终结果 “acb”。
 * 示例 2：
 *
 * 输入：s = "baaca", k = 3
 * 输出："aaabc"
 * 解释：
 * 在第一步中，我们将第一个字符（“b”）移动到最后，获得字符串 “aacab”。
 * 在第二步中，我们将第三个字符（“c”）移动到最后，获得最终结果 “aaabc”。
 *
 *
 * 提示：
 *
 * 1 <= k<= S.length<= 1000
 * s只由小写字母组成。
 *
 * */


class Solution {
    public String orderlyQueue(String s, int k) {
        if (k == 1){
            String min = s;
            StringBuilder SB = new StringBuilder(s);
            for (int i = 0; i < s.length(); i++) {
                SB.deleteCharAt(0);
                SB.append(s.charAt(i));
                String cur = SB.toString();
                if (cur.compareTo(min) < 0){
                    min = cur;
                }
            }
            return min;
        } else {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "xisxr";
        int k = 1;
        System.out.println(solution.orderlyQueue(s,k));
    }
}
