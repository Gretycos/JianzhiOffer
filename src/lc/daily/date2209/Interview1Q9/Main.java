package lc.daily.date2209.Interview1Q9;

/**
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成
 * （比如，waterbottle是erbottlewat旋转后的字符串）。
 *
 * 示例1:
 *
 *  输入：s1 = "waterbottle", s2 = "erbottlewat"
 *  输出：True
 *
 *
 * 示例2:
 *
 *  输入：s1 = "aa", s2 = "aba"
 *  输出：False
 *
 *
 * 提示：
 * 字符串长度在[0, 100000]范围内。
 *
 * 说明:
 * 你能只调用一次检查子串的方法吗？
 *
 * */
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        return s1.length() == s2.length() && (s1+s1).contains(s2);
    }

    public boolean isFlipedString2(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int n = s1.length();
        if (n == 0) return true;
        boolean flag;
        for (int i = 0; i < n; i++) {
            flag = true;
            for (int j = 0; j < n; j++) {
                if (s1.charAt( (i + j) % n) != s2.charAt(j)){
                    flag = false;
                    break;
                }
            }
            if (flag){
                return true;
            }
        }
        return false;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "waterbottle", s2 = "erbottlewat";
        System.out.println(solution.isFlipedString(s1,s2));
    }
}
