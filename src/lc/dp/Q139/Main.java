package lc.dp.Q139;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author Tsong
 * @Date 2023/9/17 19:26
 */
class Solution {
    private String s;
    private Set<String> wordDict;
    private int[] memo;
    public boolean wordBreak(String s, List<String> wordDict) {
        this.s = s;
        this.wordDict = new HashSet<>(wordDict);
        int n = s.length();
        memo = new int[n];
        Arrays.fill(memo, -1);
        return dp(n-1);
    }

    // dp(i): s[0..i]能拆分成词典中的词
    private boolean dp(int i) {
        if (i < 0) return true;
        if (memo[i] != -1) return memo[i] == 1;
        boolean res = false;
        for (int j = -1; j < i; j++) {
            res = dp(j) && wordDict.contains(s.substring(j+1, i+1));
            if (res){
                break;
            }
        }
        memo[i] = res ? 1 : 0;
        return memo[i] == 1;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> dict = List.of("leet", "code");
        String s = "leetcode";
        System.out.println(solution.wordBreak(s, dict));
    }
}