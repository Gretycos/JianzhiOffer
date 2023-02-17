package lc.daily.date2302.Q1139;
class Solution {
    private static class State{
        int x, y;
    }
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        State[][] dp = new State[m+1][n+1];
        for (State[] row : dp) {
            for (int j = 0; j < row.length; j++) {
                row[j] = new State();
            }
        }
        // 横向和纵向连续1的个数
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (grid[i-1][j-1] == 0) continue;
                dp[i][j].x = dp[i][j-1].x + 1;
                dp[i][j].y = dp[i-1][j].y + 1;
            }
        }
        int maxSide = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 尝试寻找的边长
                // 这里找到的边长构成2条边(右下，左下)、（右下，右上）
                int curSide = Math.min(dp[i][j].x, dp[i][j].y);
                // 不够当前最长的边就不考虑了
                if (curSide <= maxSide) continue;
                for (; curSide > maxSide; curSide--){
                    // 判断（左下，左上）、（右上，左上）是否能构成正方形
                    // 因为curSide是从0到i or j的1的累计值，所以不会比i or j 大，不需要额外判断边界
                    if (dp[i][j-curSide+1].y >= curSide && dp[i-curSide+1][j].x >= curSide){
                        maxSide = curSide;
                        // 不需要查找更短的
                        break;
                    }
                }
            }
        }
        return maxSide * maxSide;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };
        System.out.println(solution.largest1BorderedSquare(grid));
    }
}
