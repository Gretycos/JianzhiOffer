package lc.labuladong.DynamicProgress.game.Q514;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private Map<Character, List<Integer>> map;
    private int[][] memo;
    private String ring, key;
    private int m, n;
    public int findRotateSteps(String ring, String key) {
        this.ring = ring;
        this.key = key;
        map = new HashMap<>();
        m = ring.length();
        n = key.length();
        memo = new int[m][n];
        // 把圆环上的字符的索引记录下来
        for (int i = 0; i < m; i++) {
            char c = ring.charAt(i);
            map.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
        }
        return dp(0,0);
    }

    // 定义：指针在ring[i]，输入key[j..]的最少操作数
    private int dp(int i, int j){
        // base case
        if (j == key.length()) return 0;
        // 查询备忘录
        if (memo[i][j] != 0) return memo[i][j];
        int res = Integer.MAX_VALUE;
        // ring上可能对应多个字符 key[j]
        for (int k : map.get(key.charAt(j))) {
            // 按一个方向拨动的次数
            int times = Math.abs(k-i);
            // 选择顺时针 or 逆时针
            times = Math.min(times, m - times);
            // 子问题，现在指针在ring[k]，输入key[j+1..]的最少操作次数
            int subProblem = dp(k, j + 1);
            res = Math.min(res, 1 + times + subProblem);
        }
        memo[i][j] = res;
        return res;
    }
}
public class Main {
}
