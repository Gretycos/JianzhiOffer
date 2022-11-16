package lc.labuladong.DataStructure.array.Q187;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DNA序列由一系列核苷酸组成，缩写为'A','C','G'和'T'.。
 *
 * 例如，"ACGAATTCCG"是一个 DNA序列 。
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 *
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的长度为10的序列(子字符串)。你可以按 任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * 示例 2：
 *
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 10^5
 * s[i]=='A'、'C'、'G'or'T'
 *
 * */
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        int[] nums = new int[n];
        // 把字符串转化成4进制的数字数组
        for (int i = 0; i < n; i++) {
            switch (s.charAt(i)){
                case 'A' -> nums[i] = 0;
                case 'G' -> nums[i] = 1;
                case 'C' -> nums[i] = 2;
                case 'T' -> nums[i] = 3;
            }
        }
        Set<Integer> seen = new HashSet<>();
        Set<String> res = new HashSet<>();

        // 匹配的长度
        int L = 10;
        // 进制
        int R = 4;
        // 存储R^(L-1)，用于删除数字使用
        int RL = (int)Math.pow(R,L-1);
        // 窗口内的hash值
        int windowHash = 0;

        int left = 0, right = 0;
        while(right < n){
            // 扩大窗口，相当于往右添加了一个数字
            windowHash = windowHash * R + nums[right];
            right++;

            if (right - left == L){
                if (seen.contains(windowHash)){
                    res.add(s.substring(left,right));
                }else{
                    seen.add(windowHash);
                }
                // 缩小窗口，相当于往左移除一个数字
                windowHash = windowHash - nums[left] * RL;
                left ++;
            }
        }
        return new ArrayList<>(res);
    }
}

public class Main {
}
