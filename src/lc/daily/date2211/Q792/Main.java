package lc.daily.date2211.Q792;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 给定字符串 s和字符串数组words, 返回words[i]中是s的子序列的单词个数。
 *
 * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 *
 * 例如， “ace” 是 “abcde” 的子序列。
 *
 *
 * 示例 1:
 *
 * 输入: s = "abcde", words = ["a","bb","acd","ace"]
 * 输出: 3
 * 解释: 有三个是s 的子序列的单词: "a", "acd", "ace"。
 *
 *
 * 示例 2:
 *
 * 输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * 输出: 2
 *
 *
 * 提示:
 *
 * 1 <= s.length <= 5 * 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * words[i]和 s都只由小写字母组成。
 *
 * */

class Solution {
    // Time: O(n + sum(i=0~m-1)(sizeOf(words[i]))) n == |s|, m == |words|
    public int numMatchingSubseq(String s, String[] words) {
        // 每个字母维护一个队列，每个队列存放int[]{word的下标，第几个字符}
        Deque<int[]>[] pointers = new ArrayDeque[26];
        for (int i = 0; i < 26; i++) {
            pointers[i] = new ArrayDeque<>();
        }
        // 初始化每个队列，存放[word的下标，第0个字符]
        for (int i = 0; i < words.length; i++) {
            pointers[words[i].charAt(0) - 'a'].addLast(new int[]{i,0});
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int len = pointers[c - 'a'].size(); // 当前对应字母队列的长度
            while(len > 0){
                // 当前word，word的元素指针
                int[] curChar = pointers[c - 'a'].removeFirst();
                String word = words[curChar[0]];
                // 如果元素指针还没到达尾部
                if (curChar[1] != word.length() - 1){
                    curChar[1]++;
                    // 把下一个元素加入相应队列
                    pointers[word.charAt(curChar[1]) - 'a'].addLast(curChar);
                }else{ // 完成一次匹配
                    res++;
                }
                len --;
            }
        }
        return res;
    }


}

public class Main {
}
