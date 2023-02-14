package lc.labuladong.DynamicProgress.game.Q887;

class Solution {
    private int[][] memo;
    public int superEggDrop(int k, int n) {
        memo = new int[k+1][n+1];
        return dp(k, n);
    }


    // 定义：dp(k,n)：当前有k个鸡蛋，有n层楼，最坏情况下需要丢鸡蛋的次数
    private int dp(int k, int n){
        if (k == 1) return n;
        if (n == 0) return 0;
        if (memo[k][n] != 0) return memo[k][n];

        int res = Integer.MAX_VALUE;
        // Time:O(kn^2) 超时
//        for (int i = 1; i < n+1; i++) {
//            res = Math.min(
//                    res,
//                    Math.max( // 最坏情况，所以取大值
//                            dp(k, n - i), // 鸡蛋没碎，往上找
//                            dp(k - 1, i - 1) // 鸡蛋碎了，往下找
//                    ) + 1 // 本次丢的次数
//            );
//        }
        // Time:O(knlogn)
        int lo = 1, hi = n; // lo从1开始是因为0的情况会直接返回0
        while (lo <= hi){
            int mid = (lo + hi) / 2;
            int broken = dp(k - 1, mid - 1); // 鸡蛋碎了，往下找
            int unbroken = dp(k, n - mid); // 鸡蛋没碎，往上找
            if (broken > unbroken){
                hi = mid - 1;
                res = Math.min(res, broken + 1);
            } else {
                // unbroken >= broken
                lo = mid + 1;
                res = Math.min(res, unbroken + 1);
            }
        }
        memo[k][n] = res;
        return res;
    }

    // Time:O(kn)
    public int superEggDrop2(int K, int N) {
        // 定义dp[k][m]：当前有k个鸡蛋，和最多允许m次扔鸡蛋的机会，所能确定的最高楼层数
        int[][] dp = new int[K+1][N+1];
        int m = 0;
        while (dp[K][m] < N){ // 退出时，dp[K][m] == N
            m++;
            for (int k = 1; k <= K; k++) {
                dp[k][m] = dp[k][m-1] // 鸡蛋没碎
                        + dp[k-1][m-1]  // 鸡蛋碎了
                        + 1;
            }
        }
        return m;
    }
}

public class Main {
}
