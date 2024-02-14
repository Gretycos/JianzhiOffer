package lc.dp.Q279;

import java.util.Arrays;

/**
 * @Author Tsong
 * @Date 2023/9/25 23:12
 */
class Solution {
    private int[] memo;
    public int numSquares(int n) {
        memo = new int[n+1];
        Arrays.fill(memo, -1);
        return dp(n);
    }

    private int dp(int i) {
        if (i == 0) return 0;
        if (memo[i] != -1) return memo[i];

        int min = Integer.MAX_VALUE;
        for (int j = 1; j * j <= i; j++) {
            min = Math.min(min, dp(i - j * j));
        }
        memo[i] = min + 1;
        return memo[i];
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 12;
        System.out.println(solution.numSquares(n));
    }
}
