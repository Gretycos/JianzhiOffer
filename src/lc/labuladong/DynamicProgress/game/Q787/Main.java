package lc.labuladong.DynamicProgress.game.Q787;

import java.util.*;

class Solution {
    private int src, dst;
    private int[][] memo;
    private Map<Integer, List<int[]>> inDegree;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 把中转k站转化成经过k+1条边
        k = k + 1;
        this.src = src;
        this.dst = dst;
        // k从1开始
        memo = new int[n][k+1];
        for (int[] row : memo) {
            Arrays.fill(row, -666);
        }
        inDegree = new HashMap<>();
        for (int[] flight : flights) {
            int from = flight[0], to = flight[1], price = flight[2];
            inDegree.computeIfAbsent(to, key -> new ArrayList<>()).add(new int[]{from, price});
        }
        // 从src出发，k步内到达dst的最小成本
        return dp(dst, k);
    }

    // 定义：从src出发，k步内到达dest的最小成本
    private int dp(int dest, int k){
        // base case
        if (dest == src){
            return 0;
        }
        if (k == 0) return -1;
        if (memo[dest][k] != -666) return memo[dest][k];

        int res = Integer.MAX_VALUE;
        List<int[]> inEdges = inDegree.get(dest);
        if (inEdges != null){
            for (int[] inEdge : inEdges) {
                int from = inEdge[0], price = inEdge[1];
                int subProblem = dp(from, k-1);
                // -1为无解
                if (subProblem != -1){
                    res = Math.min(res, subProblem + price);
                }
            }
        }
        memo[dest][k] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[dest][k];
    }
}

public class Main {
}
