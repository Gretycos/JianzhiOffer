package lc.hot100.Q51to75.Q279;

/**
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
 * 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 *
 * 示例1：
 *
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 *
 *
 * 示例 2：
 *
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * 提示：
 *
 * 1 <= n <= 10^4
 *
 *
 * */

class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            // 设最大值是+1+1...+1来的
            dp[i] = i;
            for (int j = 1; i - j*j >= 0; j++) {
                // dp(i) = min{dp(i-j^2)+dp(j^2)(这个值恒等于1)}
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }
        return dp[n];
    }

    // 四平方定理
    public int numSquares2(int n) {
        if (isPerfectSquare(n)){
            return 1;
        }
        // n == 4*(8m+7)
        if (check4Square(n)){
            return 4;
        }
        for (int i = 1; i * i <= n ; i++) {
            int j = n - i * i;
            if (isPerfectSquare(j)){
                return 2;
            }
        }
        return 3;
    }

    private boolean isPerfectSquare(int n){
        int x = (int) Math.sqrt(n);
        return x * x == n;
    }

    private boolean check4Square(int n){
        while (n % 4 == 0){
            n /= 4;
        }
        return n % 8 == 7;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSquares(7));
    }
}
