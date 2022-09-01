package lc.hot100.Q1to25.Q17;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 1: null    2: a,b,c  3: d,e,f
 * 4: g,h,i   5: j,k,l  6: m,n,o
 * 7: p,q,r,s 8: t,u,v  9: w,x,y,z
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 *
 * 示例 3：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 *
 * 提示：
 *
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 *
 * */

class Solution {
    private final String[] nums = {
            "abc","def",
            "ghi","jkl","mno",
            "pqrs","tuv","wxyz"
    };
    private List<String> res;
    private String digits;
    private StringBuilder sb;
    private void dfs(int p){
        if (p == digits.length()){
            res.add(sb.toString());
            return;
        }
        String s = nums[digits.charAt(p)-'2'];
        for (int i = 0; i < s.length();i++){
            sb.append(s.charAt(i));
            dfs(p+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        this.digits = digits;
        if (digits.length() == 0) return res;
        sb = new StringBuilder();
        dfs(0);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.letterCombinations(""));
    }
}
