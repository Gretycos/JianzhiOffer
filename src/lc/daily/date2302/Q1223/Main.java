package lc.daily.date2302.Q1223;

class Solution {
    private int n;
    private int[] rollMax;
    private int[][][] memo;
    private final int MOD = (int) 1e9+7;
    public int dieSimulator(int n, int[] rollMax) {
        this.n = n;
        this.rollMax = rollMax;
        memo = new int[n][7][16];
        return dfs(0,0,0);
    }

    // 定义：第i轮，掷出点数为j，连续次数为x的方案数
    private int dfs(int i, int j, int x){
        // base case
        if (i == n){
            return 1;
        }
        if (memo[i][j][x] != 0){
            return memo[i][j][x];
        }
        long sum = 0;
        for (int p = 1; p <= 6; p++) {
            if (p != j){
                sum += dfs(i+1, p, 1);
            } else if (x < rollMax[j-1]){
                sum += dfs(i+1, p, x+1);
            }
        }
        memo[i][j][x] = (int) (sum % MOD);
        return memo[i][j][x];
    }
}

public class Main {
}
