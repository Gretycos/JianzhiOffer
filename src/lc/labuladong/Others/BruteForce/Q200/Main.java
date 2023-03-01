package lc.labuladong.Others.BruteForce.Q200;

class Solution {
    private char[][] grid;
    private int m, n;
    private final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int numIslands(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1'){
                    // 每发现一个岛屿，岛屿数量加一
                    res ++;
                    // 然后使用 DFS 将岛屿淹了
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
        if (grid[i][j] == '0'){
            // 已经是海水了
            return;
        }

        // 将 (i, j) 变成海水
        grid[i][j] = '0';
        // 淹没上下左右的陆地
        for (int k = 0; k < 4; k++) {
            int x = i + dir[k][0], y = j + dir[k][1];
            dfs(x, y);
        }
    }
}

public class Main {
}
