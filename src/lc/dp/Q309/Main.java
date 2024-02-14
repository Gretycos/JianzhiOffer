package lc.dp.Q309;

/**
 * @Author Tsong
 * @Date 2023/9/29 17:35
 */

class Solution {
    private int[][] memo;
    private int[] prices;
    public int maxProfit(int[] prices) {
        this.prices = prices;
        int n = prices.length;
        memo = new int[n][];
        return dp(n-1)[0];
    }

    private int[] dp(int i) {
        if (i == -2) return new int[]{0, 0};
        if (i == -1) return new int[]{0, Integer.MIN_VALUE};
        if (memo[i] != null) return memo[i];
        int i_notHold = Math.max(dp(i-1)[0], dp(i-1)[1] + prices[i]);
        int i_Hold = Math.max(dp(i-1)[1], dp(i-2)[0] - prices[i]);

        memo[i] = new int[]{i_notHold, i_Hold};
        return memo[i];
    }
}
public class Main {
    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        Solution solution = new Solution();
        System.out.println(solution.maxProfit(prices));
    }
}
