package lc.labuladong.DataStructure.graph.Q886;

import java.util.LinkedList;
import java.util.List;

class Solution {
    private boolean bipartite = true;
    private boolean[] visited;
    private boolean[] colors;
    public boolean possibleBipartition(int n, int[][] dislikes) {
        // 顶点标号从1开始
        visited = new boolean[n+1];
        colors = new boolean[n+1];
        List<Integer>[] graph = buildGraph(n, dislikes);
        for (int v = 1; v <= n; v++) {
            if (!visited[v]){
                traverse(graph, v);
            }
        }
        return bipartite;
    }

    private void traverse(List<Integer>[] graph, int v){
        if (!bipartite) return;
        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]){
                colors[w] = !colors[v];
                traverse(graph, w);
            }else{
                if (colors[w] == colors[v]){
                    bipartite = false;
                    return;
                }
            }
        }
    }

    private List<Integer>[] buildGraph(int n, int[][] dislikes){
        List<Integer>[] graph = new LinkedList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : dislikes) {
            int v = edge[0], w = edge[1];
            // 无向图
            graph[v].add(w);
            graph[w].add(v);
        }
        return graph;
    }
}

public class Main {
}
