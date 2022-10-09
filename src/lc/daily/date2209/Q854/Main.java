package lc.daily.date2209.Q854;

/**
 * 对于某些非负整数 k ，如果交换 s1 中两个字母的位置恰好 k 次，能够使结果字符串等于 s2 ，则认为字符串 s1 和 s2 的 相似度为 k 。
 *
 * 给你两个字母异位词 s1 和 s2 ，返回 s1 和 s2 的相似度 k 的最小值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s1 = "ab", s2 = "ba"
 * 输出：1
 *
 *
 * 示例 2：
 *
 * 输入：s1 = "abc", s2 = "bca"
 * 输出：2
 *
 *
 * 提示：
 * 1 <= s1.length <= 20
 * s2.length == s1.length
 * s1和s2只包含集合{'a', 'b', 'c', 'd', 'e', 'f'}中的小写字母
 * s2 是 s1 的一个字母异位词
 *
 * */

class Solution {
    private int res;
    private int n;
    private char[] S1,S2;
    public int kSimilarity(String s1, String s2) {
        n = s1.length();
        S1 = s1.toCharArray();
        S2 = s2.toCharArray();
        res = Integer.MAX_VALUE;
        dfs(0,0);
        return res;
    }

    private void dfs(int start, int cur){
        if (cur >= res) return;
        if (start == n-1){
            res = Math.min(res,cur);
            return;
        }

        for (int i = start; i < n; i++){
            if (S1[i] != S2[i]){ // 需要交换
                for (int j = i+1; j < n; j++) { // 查找与S2[i]相同的字符S1[j]
                    if (S1[j] == S2[i] && S1[j] != S2[j]){ // S1[j] == S2[j] 说明S1[j]已经稳定，不需要交换，继续往后找
                        swap(S1,i,j);
                        dfs(start+1,cur+1);
                        swap(S1,i,j);
                        // 最优解，此时交叉相等，交换一次可以匹配2个字符
                        // 不需要再搜了，所以break 退出forj循环
                        if (S2[j] == S1[i]) break;
                    }
                }
                // 内层循环结束之后，立即返回
                // 这是因为i位置处理结束之后，会swap回去，如果不返回，会导致i++，
                // 这样前一个位置是未处理的状态，又进入下一层，产生错误结果
                return;
            }
        }
        // 后续没有需要交换的，则更新res
        res = Math.min(cur,res);
    }

    private void swap(char[] s, int i, int j){
        char c = s[i];
        s[i] = s[j];
        s[j] = c;
    }
}

public class Main {
    public static void main(String[] args) {
        String s1 = "abccaacceecdeea", s2 = "bcaacceeccdeaae";
        Solution solution = new Solution();
        System.out.println(solution.kSimilarity(s1,s2));
    }
}
