package lc.daily.date2210.Q1790;

/**
 * 给你长度相等的两个字符串 s1 和 s2 。
 * 一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
 *
 * 如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s1 = "bank", s2 = "kanb"
 * 输出：true
 * 解释：例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
 *
 *
 * 示例 2：
 *
 * 输入：s1 = "attack", s2 = "defend"
 * 输出：false
 * 解释：一次字符串交换无法使两个字符串相等
 *
 *
 * 示例 3：
 *
 * 输入：s1 = "kelb", s2 = "kelb"
 * 输出：true
 * 解释：两个字符串已经相等，所以不需要进行字符串交换
 *
 *
 * 示例 4：
 *
 * 输入：s1 = "abcd", s2 = "dcba"
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= s1.length, s2.length <= 100
 * s1.length == s2.length
 * s1 和 s2 仅由小写英文字母组成
 *
 * */

class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            // s1[i] != s2[i]，说明需要交换
            if (s1.charAt(i) != s2.charAt(i)){
                // 往后寻找交叉相等的进行交换
                for (int j = i+1; j < n; j++) {
                    if (s1.charAt(j) != s2.charAt(j)){
                        if (s1.charAt(i) == s2.charAt(j) && s2.charAt(i) == s1.charAt(j)){
                            if (++count > 1) return false;
                        }else return false;
                    }
                }
                // 没有交换
                if (count == 0) return false;
            }
        }
        return true;
    }

    public boolean areAlmostEqual2(String s1, String s2){
        int j = -1; // 记录不同的下标(出现1次以上为-2)
        for (int i = 0; i < s1.length(); i++) {
            char a = s1.charAt(i), b = s2.charAt(i);
            if (a != b){
                if (j == -1){ // 记录第一次不同的下标
                    j = i;
                }else if (j == -2){ // 第二处不同之后又有不同
                    return false;
                }else{
                    // 不满足交叉不同
                    if (a != s2.charAt(j) || b != s1.charAt(j)){
                        return false;
                    }
                    j = -2; // 第二处不同
                }
            }
        }
        return j < 0;
    }
}


public class Main {
}
