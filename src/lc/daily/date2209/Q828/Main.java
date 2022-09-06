package lc.daily.date2209.Q828;

import java.util.Arrays;

/**
 * 我们定义了一个函数 countUniqueChars(s) 来统计字符串 s 中的唯一字符，并返回唯一字符的个数。
 *
 * 例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，
 * 因为它们只出现一次，所以 countUniqueChars(s) = 5 。
 *
 * 本题将会给你一个字符串 s ，我们需要返回 countUniqueChars(t) 的总和，
 * 其中 t 是 s 的子字符串。输入用例保证返回值为32 位整数。
 *
 * 注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串
 * （也就是说，你必须统计 s 的所有子字符串中的唯一字符）。
 *
 *
 *
 * 示例 1：
 *
 * 输入: s = "ABC"
 * 输出: 10
 * 解释: 所有可能的子串为："A","B","C","AB","BC" 和 "ABC"。
 *      其中，每一个子串都由独特字符构成。
 *      所以其长度总和为：1 + 1 + 1 + 2 + 2 + 3 = 10
 *
 *
 * 示例 2：
 *
 * 输入: s = "ABA"
 * 输出: 8
 * 解释: 除了 countUniqueChars("ABA") = 1 之外，其余与示例 1 相同。
 *
 *
 * 示例 3：
 *
 * 输入：s = "LEETCODE"
 * 输出：92
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * s 只包含大写英文字符
 *
 * */


class Solution {
    public int uniqueLetterString(String s) {
        int res = 0;
        int n = s.length();
        int[] pre = new int[26]; //存储某一个字符前一个字符所在位置
        int[] cur = new int[26]; //存储某个字符当前所处位置

        Arrays.fill(pre, -1);
        Arrays.fill(cur, -1);

        // [j..i..k]
        // 对每一个字符i，向前找到相同的字符j，向后找到相同的字符k。当前字符对最终结果的贡献是：（i-j）* (k-i)。
        // 字符串拼接之后，字符i对子串(j~k)的贡献度符合乘法公式
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // 当前字符出现过，以当前字符为右边界，计算上一次出现的该字符的贡献度
            if (cur[c - 'A'] > -1) {
                res += (cur[c - 'A'] - pre[c - 'A']) * (i - cur[c - 'A']);
            }
            pre[c - 'A'] = cur[c - 'A'];
            cur[c - 'A'] = i;
        }

        // 计算最后出现的每个字符的贡献度
        // 因为前面那个循环要么不计算，要么计算的是上一次出现的字符的贡献度
        for (int i = 0; i < 26; i++) {
            if (cur[i] > -1) {
                res += (cur[i] - pre[i]) * (n - cur[i]);
            }
        }

        return res;

    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "LEETCODE";
        System.out.println(solution.uniqueLetterString(s));
    }
}
