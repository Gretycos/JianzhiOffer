package com.daily.Q761;

import java.util.ArrayList;
import java.util.List;

/**
 * 特殊的二进制序列是具有以下两个性质的二进制序列：
 *
 * 0 的数量与 1 的数量相等。
 * 二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。
 * 给定一个特殊的二进制序列S，以字符串形式表示。定义一个操作 为首先选择S的两个连续且非空的特殊的子串，然后将它们交换。
 * （两个子串为连续的当且仅当第一个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。)
 *
 * 在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？
 *
 * 示例 1:
 *
 * 输入: S = "11011000"
 * 输出: "11100100"
 * 解释:
 * 将子串 "10" （在S[1]出现） 和 "1100" （在S[3]出现）进行交换。
 * 这是在进行若干次操作后按字典序排列最大的结果。
 * 说明:
 *
 * S的长度不超过50。
 * S保证为一个满足上述定义的特殊 的二进制序列。
 *
 * */

class Solution {
    public String makeLargestSpecial(String s) {
        if (s.length() <= 2){
            return s;
        }
        int score = 0, start = 0;
        List<String> subs = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            // 1：得分+1，0：得分-1
            score += s.charAt(i) == '1' ? 1 : -1;
            // 得分为0说明匹配出一个特殊序列
            if (score == 0){
                // 此时i指向完成匹配的最后一个0
                // 需要去掉特殊序列的收尾的1和0，递归操作
                subs.add("1"+makeLargestSpecial(s.substring(start+1,i))+"0");
                start = i + 1;
            }
        }
        // 把内部包含的子特殊序列按字母序降序排序
        subs.sort((a,b) -> b.compareTo(a));
        StringBuilder res = new StringBuilder();
        for(String sub: subs){
            res.append(sub);
        }
        return res.toString();
    }


}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.makeLargestSpecial("1011011000"));
    }
}
