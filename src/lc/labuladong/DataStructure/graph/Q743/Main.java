package lc.labuladong.DataStructure.graph.Q743;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class State{
    int id;
    int distFromStart;

    public State(int id, int distFromStart) {
        this.id = id;
        this.distFromStart = distFromStart;
    }
}
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] graph = new LinkedList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : times) {
            int from = edge[0], to = edge[1], weight = edge[2];
            graph[from].add(new int[]{to, weight});
        }

        int[] distTo = dijkstra(k, graph);

        int res = 0;
        for (int i = 1; i < distTo.length; i++) {
            // 出现不可达节点
            if (distTo[i] == Integer.MAX_VALUE){
                return -1;
            }
            res = Math.max(res, distTo[i]);
        }
        return res;
    }

    private int[] dijkstra(int start, List<int[]>[] graph){
        int[] distTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>(
                (s1, s2)->s1.distFromStart - s2.distFromStart
        );
        pq.add(new State(start, 0));

        while (!pq.isEmpty()){
            State curState = pq.remove();
            int curId = curState.id;
            int curDistFromStart = curState.distFromStart;
            //
            if (curDistFromStart > distTo[curId]) continue;
            // 遍历cur的邻居next
            for (int[] next : graph[curId]) {
                int nextId = next[0];
                int distToNext = distTo[curId] + next[1];
                // 起点到next的距离比刚计算出来的distToNext要大，则更新distTo[nextId]
                if (distTo[nextId] > distToNext){
                    distTo[nextId] = distToNext;
                    // 加入优先队列
                    // 会出现重复节点，不影响正确性
                    pq.add(new State(nextId, distToNext));
                }
            }
        }

        return distTo;
    }
}

public class Main {
}
