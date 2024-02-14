package lc.dp.Q131;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Tsong
 * @Date 2023/9/16 23:11
 */

class Solution {
    private boolean[][] isPalindrome;
    private List<List<String>> res;
    private List<String> list;
    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        list = new ArrayList<>();
        buildIsPalindrome(s);
        dfs(s, 0);
        return res;
    }

    private void buildIsPalindrome(String s) {
        int n = s.length();
        isPalindrome = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        isPalindrome[i][j] = true;
                    } else {
                        if (i + 1 < n && j - 1 > i){
                            isPalindrome[i][j] = isPalindrome[i + 1][j - 1];
                        }
                    }
                }
            }
        }
    }

    private void dfs(String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }

        // 回溯找子串
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome[start][end]) {
                list.add(s.substring(start, end + 1));
                // 下一层的起点要是本轮终点的下一个，否则会重复
                dfs(s, end + 1);
                list.remove(list.size() - 1);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aab";
        System.out.println(solution.partition(s));
    }
}
