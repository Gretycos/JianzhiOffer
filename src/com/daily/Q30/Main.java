package com.daily.Q30;

/**
 * 给定一个字符串s和一些 长度相同 的单词words 。找出 s 中恰好可以由words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑words中单词串联的顺序。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 *
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 示例 3：
 *
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i]由小写英文字母组成
 *
 * */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    /**
        滑动窗口+双HashMap优化
        关键在于只需要遍历0-wordLen-1为起始索引的子串,然后遍历边维护符合条件res
        具体逻辑可以看代码,核心就是动态维护根据cur的数目动态查找合法子串
        N 是s的长度，wordLen是单个word的长度，wordsLen是words的长度
        时间复杂度:O(wordLen * N),空间复杂度:O(wordsLen)
    */
    public List<Integer> findSubstring(String s, String[] words) {
        // 结果集
        List<Integer> res = new ArrayList<>();

        // 阴间案例
        if(s == null || s.length() == 0 || words == null || words.length == 0) {
            return res;
        }

        int len = s.length(); // s的长度
        int wordsNum = words.length; // 单词总个数
        int wordLen = words[0].length(); // 每个单词长度

        // words总长度 > s的长度
        if(wordsNum * wordLen > len) {
            return res;
        }

        // 存储words的单词和个数
        Map<String, Integer> allWords = new HashMap<>();
        for(String word : words) {
            // 找不到当前单词肯定没有
            if(!s.contains(word)) {
                return res;
            }
            allWords.put(word, allWords.getOrDefault(word, 0) + 1);
        }

        // 只需要遍历wordLen种起点:0~wordLen-1
        for(int i = 0; i < wordLen; i++) {
            // 左右指针以及当前窗口中符合的单词个数
            int left = i, right = i, count = 0;
            // 存储[left,right)内符合条件的单词及其数量
            Map<String, Integer> curWords = new HashMap<>();
            // right移动的右边界为len(含)
            while(right + wordLen <= len) {
                // 当前要考虑的单词部分[right,right+wordLen)
                String curWord = s.substring(right, right + wordLen);
                // 选了当前单词就要移动右指针
                right += wordLen;

                // 1.allWords里有curWord,说明是合法的,可以加入
                if(allWords.containsKey(curWord)) {
                    curWords.put(curWord, curWords.getOrDefault(curWord, 0) + 1);
                    // 有效单词数+1
                    count++;
                    // 有一种特殊情况是有curWord,但其数目超过了上限,需要一直舍弃左边的直至合法
                    while(curWords.get(curWord) > allWords.get(curWord)) {
                        // 要移除的单词
                        String delWord = s.substring(left, left + wordLen);
                        // 更新haswords
                        curWords.put(delWord, curWords.get(delWord) - 1);
                        // 同时移动左指针
                        left += wordLen;
                        // count数目-1
                        count--;
                    }

                // 2.allWords里有没curWord,说明curWord是不合法的,得将left指针移动到新的right后面
                } else {
                    left = right;
                    // 清空curWords
                    curWords.clear();
                    // 清空count
                    count = 0;
                }

                // 每当count更新一次就判断是否符合预期
                if(count == wordsNum) {
                    res.add(left);
                }
            }
        }
        return res;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar","foo","the"};
        System.out.println(solution.findSubstring(s,words));
    }
}
