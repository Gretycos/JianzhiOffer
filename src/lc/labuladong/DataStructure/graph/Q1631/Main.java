package lc.labuladong.DataStructure.graph.Q1631;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class State{
    int x, y;
    int effortFromStart;

    public State(int x, int y, int effortFromStart) {
        this.x = x;
        this.y = y;
        this.effortFromStart = effortFromStart;
    }
}

class Solution {
    private final int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        // 从(0,0) -> (i,j)的最小体力耗费是effortTo[i][j]
        int[][] effortTo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(effortTo[i], Integer.MAX_VALUE);
        }
        effortTo[0][0] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>(
                (s1, s2)->s1.effortFromStart - s2.effortFromStart
        );

        pq.add(new State(0,0,0));

        while (!pq.isEmpty()){
            State curState = pq.remove();
            int curX = curState.x, curY = curState.y;
            int curEffortFromStart = curState.effortFromStart;
            // 抵达终点
            if (curX == m - 1 && curY == n - 1){
                return curEffortFromStart;
            }
            if (curEffortFromStart > effortTo[curX][curY]) continue;
            for (int[] neighbor : adj(heights, curX, curY)) {
                int nextX = neighbor[0], nextY = neighbor[1];
                // 路径消耗的体力值是路径相邻格子高度差绝对值的最大值
                int effortToNext = Math.max(
                        effortTo[curX][curY], // 否则，是到(curX,curY)的体力耗费
                        Math.abs(heights[curX][curY]-heights[nextX][nextY])
                );
                if (effortTo[nextX][nextY] > effortToNext){
                    effortTo[nextX][nextY] = effortToNext;
                    pq.add(new State(nextX,nextY, effortToNext));
                }
            }
        }
        return -1;
    }

    private List<int[]> adj(int[][] matrix, int x, int y){
        int m = matrix.length, n = matrix[0].length;
        List<int[]> neighbors = new ArrayList<>();
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx >= m || nx < 0 || ny >= n || ny < 0) continue;
            neighbors.add(new int[]{nx, ny});
        }
        return neighbors;
    }
}


public class Main {
}
