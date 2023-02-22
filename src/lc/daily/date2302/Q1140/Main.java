package lc.daily.date2302.Q1140;

class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length, sum = 0;
        // 定义：dp[i][j] 为 区间剩下[i..]，M = j时，先手的最大收益
        int[][] dp = new int[n][n+1];
        for (int i = n-1; i >=0 ; i--) {
            // 后缀和
            sum += piles[i];
            for (int M = 1; M <= n; M++) {
                if (i + 2 * M >= n){
                    // 超出范围，说明最优情况是全部取走，即sum
                    dp[i][M] = sum;
                } else {
                    // 无法全部取走，需要让本轮的后手拿最少
                    // 本轮的后手是下一轮的先手：拿 dp[i+x][Math.max(M,x)]
                    // 本轮的先手是下一轮的后手：拿 sum - dp[i+x][Math.max(M,x)]
                    for (int x = 1; x <= 2 * M; x++) {
                        dp[i][M] = Math.max(dp[i][M], sum - dp[i+x][Math.max(M,x)]);
                    }
                }
            }
        }
        return dp[0][1];
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] piles = {2,7,9,4,4};
        System.out.println(solution.stoneGameII(piles));
    }
}
