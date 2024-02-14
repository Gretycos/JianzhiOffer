package lc.dp.Q313;

/**
 * @Author Tsong
 * @Date 2023/9/29 18:41
 */
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        long[] dp = new long[n];
        dp[0] = 1;
        int m = primes.length;
        int[] p = new int[m];
        for (int i = 1; i < n; i++) {
            long min = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                min = Math.min(min, primes[j] * dp[p[j]]);
            }
            dp[i] = min;
            for (int x = 0; x < p.length; x++) {
                if (min == primes[x] * dp[p[x]]) p[x]++;
            }
        }
        return (int) dp[n-1];
    }
}

public class Main {
}
