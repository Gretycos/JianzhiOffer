package lc.daily.date2302.Q1129;

import java.util.*;

class State{
    int id; // 图的节点id
    int color; // 入边的颜色

    public State(int id, int color){
        this.id = id;
        this.color = color;
    }
}

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[][] graph = buildGraph(n, redEdges, blueEdges);
        return bfs(n,0, graph);
    }

    private int[] bfs(int n, int start, List<Integer>[][] graph){
        int[] distTo = new int[n];
        Arrays.fill(distTo, -1);
        boolean[][] visited = new boolean[2][n];
        // Queue
        Deque<State> q = new ArrayDeque<>();
        q.add(new State(0, 0)); // 起点入边为红，出边为蓝
        q.add(new State(0, 1)); // 起点入边为蓝，出边为红

        int d = 0;
        while (!q.isEmpty()){
            // 层序遍历
            for (int size = q.size(); size > 0; size--) {
                State curState = q.remove();
                int id = curState.id, color = curState.color;
                if (distTo[id] == -1){
                    distTo[id] = d;
                }
                visited[color][id] = true;
                int nextColor = 1 - color;
                for (int nextId : graph[nextColor][id]) {
                    if (!visited[nextColor][nextId]){
                        q.add(new State(nextId, nextColor));
                    }
                }
            }
            // 每一层距离+1
            d++;
        }
        return distTo;
    }

    private List<Integer>[][] buildGraph(int n, int[][] redEdges, int[][] blueEdges){
        List<Integer>[][] graph = new List[2][n];
        for (List<Integer>[] edges : graph) {
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new LinkedList<>();
            }
        }
        for (int[] redEdge : redEdges) {
            int from = redEdge[0], to = redEdge[1];
            graph[0][from].add(to);
        }
        for (int[] blueEdge : blueEdges) {
            int from = blueEdge[0], to = blueEdge[1];
            graph[1][from].add(to);
        }
        return graph;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 5;
        int[][] red_edges = {
                {0,1},{1,2},{2,3},{3,4}
        };
        int[][] blue_edges = {
                {1,2},{2,3},{3,1}
        };
        System.out.println(Arrays.toString(solution.shortestAlternatingPaths(n, red_edges, blue_edges)));
    }
}
