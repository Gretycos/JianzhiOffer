package lc.daily.date2210.Q934;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给你一个大小为 n x n 的二元矩阵 grid ，其中 1 表示陆地，0 表示水域。
 *
 * 岛 是由四面相连的 1 形成的一个最大组，即不会与非组内的任何其他 1 相连。grid 中 恰好存在两座岛 。
 *
 * 你可以将任意数量的 0 变为 1 ，以使两座岛连接起来，变成 一座岛 。
 *
 * 返回必须翻转的 0 的最小数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [
 *          [0,1],
 *          [1,0]
 *          ]
 * 输出：1
 *
 *
 * 示例 2：
 *
 * 输入：grid = [
 *          [0,1,0],
 *          [0,0,0],
 *          [0,0,1]
 *          ]
 * 输出：2
 *
 *
 * 示例 3：
 *
 * 输入：grid = [
 *          [1,1,1,1,1],
 *          [1,0,0,0,1],
 *          [1,0,1,0,1],
 *          [1,0,0,0,1],
 *          [1,1,1,1,1]
 *          ]
 * 输出：1
 *
 *
 * 提示：
 *
 * n == grid.length == grid[i].length
 * 2 <= n <= 100
 * grid[i][j] 为 0 或 1
 * grid 中恰有两个岛
 *
 * */
class Solution {
    private int[][] grid;
    private int m, n;
    private Deque<int[]> queue; // island1 的沿海节点
    private final int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};

    public int shortestBridge(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            boolean foundIsland = false; // 找到1个岛屿
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1){
                    dfs(i,j);
                    foundIsland = true;
                    break;
                }
            }
            if (foundIsland){
                break;
            }
        }
        // bfs搜另一个岛屿
        return bfs();
    }

    private void dfs(int i, int j){
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1){
            return;
        }
        grid[i][j] = -1;
        if (isNextToSea(i,j)){
            queue.add(new int[]{i,j}); // 添加沿海节点
        }
        dfs(i-1,j);
        dfs(i,j+1);
        dfs(i+1,j);
        dfs(i,j-1);
    }

    private boolean isNextToSea(int i, int j){
        return (i - 1 >= 0 && grid[i-1][j] == 0)
                || (j + 1 < n && grid[i][j+1] == 0)
                || (i + 1 < m && grid[i+1][j] == 0)
                || (j - 1 >= 0 && grid[i][j-1] == 0);
    }

    private int bfs(){
        int dist = 0;
        // 层序遍历
        while(!queue.isEmpty()){
            int curSize = queue.size();
            for (int size = 0; size < curSize; size++){
                int[] node = queue.removeFirst();
                // 四个方向
                for (int d = 0; d < 4; d++){
                    int x = node[0] + dir[d][0];
                    int y = node[1] + dir[d][1];
                    if (x >= 0 && x < m && y >= 0 && y < n){
                        if (grid[x][y] == 1){
                            return dist;
                        }
                        if (grid[x][y] == 0){
                            queue.addLast(new int[]{x,y});
                            grid[x][y] = -2;
                        }
                    }
                }
            }
            // 本层节点遍历完了
            dist++;
        }
        return dist;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] grid = {
                {0,1,0,0,0},
                {0,1,0,1,1},
                {0,0,0,0,1},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };
        Solution solution = new Solution();
        System.out.println(solution.shortestBridge(grid));
    }
}
