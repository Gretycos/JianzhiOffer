package lc.labuladong.Others.BruteForce.Q695;

class Solution {
    private int[][] grid;
    private int m, n;
    private final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1){
                    res = Math.max(res, dfs(i, j));
                }
            }
        }
        return res;
    }

    private int dfs( int i, int j){
        if (i < 0 || i >= m || j < 0 || j >= n){
            // 超出索引边界
            return 0;
        }
        if (grid[i][j] == 0){
            // 已经是海水了
            return 0;
        }

        // 将 (i, j) 变成海水
        grid[i][j] = 0;
        int area = 1; // 初始面积为1
        // 淹没上下左右的陆地
        for (int k = 0; k < 4; k++) {
            int x = i + dir[k][0], y = j + dir[k][1];
            area += dfs(x, y);
        }

        return area;
    }
}

public class Main {
}
