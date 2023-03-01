package lc.labuladong.Others.BruteForce.Q694;

import java.util.HashSet;
import java.util.Set;

class Solution{
    private int[][] grid;
    private int m, n;
    private final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        Set<String> islands = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1){
                    StringBuilder sb = new StringBuilder();
                    dfs(i, j, sb, 666);
                    islands.add(sb.toString());
                }
            }
        }
        return islands.size();
    }

    private void dfs(int i, int j, StringBuilder sb, int d){
        if (i < 0 || i >= m || j < 0 || j >= n){
            // 超出索引边界
            return;
        }
        if (grid[i][j] == 0){
            // 已经是海水了
            return;
        }

        // 前序遍历位置：进入 (i, j)
        // 将 (i, j) 变成海水
        grid[i][j] = 0;
        sb.append(d).append(',');
        // 淹没上下左右的陆地
        for (int k = 0; k < 4; k++) {
            int x = i + dir[k][0], y = j + dir[k][1];
            dfs(x, y, sb, k+1);
        }
        // 后序遍历位置：离开 (i, j)
        sb.append(-d).append(',');
    }
}
public class Main {
}
