package lc.labuladong.DataStructure.array.Q567;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你两个字符串s1和s2 ，写一个函数来判断 s2 是否包含 s1的排列。如果是，返回 true ；否则，返回 false 。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 *
 * 示例 2：
 *
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= s1.length, s2.length <= 10^4
 * s1 和 s2 仅包含小写字母
 *
 * */
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character,Integer> need = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            need.put(c, need.getOrDefault(c,0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;

        while (right < s2.length()){
            char c = s2.charAt(right);
            right ++;
            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0) + 1);
                if ((int)(window.get(c)) == need.get(c)){
                    valid++;
                }
            }
            // 因为排列，长度需相同
            while (right - left >= s1.length()){
                if (valid == need.size()){
                    return true;
                }
                char d = s2.charAt(left);
                left ++;
                if (need.containsKey(d)){
                    if ((int)(window.get(d)) == need.get(d)){
                        valid--;
                    }
                    window.put(d,window.getOrDefault(d,0) - 1);
                }
            }
        }
        return false;
    }
}
public class Main {
}
