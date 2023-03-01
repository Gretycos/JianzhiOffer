package lc.labuladong.Others.BruteForce.Q1254;

class Solution {
    private int[][] grid;
    private int m, n;
    private final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int closedIsland(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        for (int j = 0; j < n; j++) {
            // 把靠上边的岛屿淹掉
            dfs(0, j);
            // 把靠下边的岛屿淹掉
            dfs(m-1, j);
        }
        for (int i = 0; i < m; i++) {
            // 把靠左边的岛屿淹掉
            dfs(i, 0);
            // 把靠右边的岛屿淹掉
            dfs(i, n-1);
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0){
                    res++;
                    dfs(i, j);
                }
            }
        }
        return res;
    }

    private void dfs( int i, int j){
        if (i < 0 || i >= m || j < 0 || j >= n){
            // 超出索引边界
            return;
        }
        if (grid[i][j] == 1){
            // 已经是海水了
            return;
        }

        // 将 (i, j) 变成海水
        grid[i][j] = 1;
        // 淹没上下左右的陆地
        for (int k = 0; k < 4; k++) {
            int x = i + dir[k][0], y = j + dir[k][1];
            dfs(x, y);
        }
    }
}

public class Main {
}
