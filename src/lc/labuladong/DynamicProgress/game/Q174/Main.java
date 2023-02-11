package lc.labuladong.DynamicProgress.game.Q174;

import java.util.Arrays;

class Solution {
    private int[][] memo;
    private int[][] dungeon;
    private int m, n;
    public int calculateMinimumHP(int[][] dungeon) {
        m = dungeon.length;
        n = dungeon[0].length;
        memo = new int[m][n];
        for (int[] rows : memo) {
            Arrays.fill(rows, -1);
        }
        this.dungeon = dungeon;
        return dp(0,0);
    }

    /**定义：dp(i,j)为从(i,j)走到终点所需的最少生命值*/
    private int dp(int i, int j){
        // base case
        if (i == m-1 && j == n-1){
            return dungeon[i][j] > 0 ? 1 : 1 - dungeon[i][j];
        }
        if (i == m || j == n) return Integer.MAX_VALUE;
        // 去除重叠子问题
        if (memo[i][j] != -1) return memo[i][j];
        int res = Math.min(dp(i, j+1), dp(i+1, j)) - dungeon[i][j];
        // 如果res <= 0，说明在(i,j)处将会吃到红药加血，导致逆推出(i,j)到终点的血量为负数
        // 骑士血量至少为1
        memo[i][j] = res <= 0 ? 1 : res;
        return memo[i][j];
    }
}

public class Main {
}
