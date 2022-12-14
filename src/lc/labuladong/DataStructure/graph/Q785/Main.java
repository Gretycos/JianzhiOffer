package lc.labuladong.DataStructure.graph.Q785;

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    // dfs
    private boolean bipartite = false;
    private boolean[] colors;
    private boolean[] visited;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        visited = new boolean[n];
        colors = new boolean[n];
        // 因为图不一定是联通的，可能存在多个子图
        // 所以要把每个节点都作为起点进行一次遍历
        // 如果发现任何一个子图不是二分图，整幅图都不算二分图
        for (int v = 0; v < n; v++) {
            if (!visited[v]){
//                traverse(graph, v);
                bfs(graph,v);
            }
        }
        return bipartite;
    }

    private void traverse(int[][] graph, int v){
        if (!bipartite) return;
        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]){
                colors[w] = !colors[v]; // 涂上不同的颜色
                traverse(graph, w);
            }else{
                if (colors[w] == colors[v]){
                    bipartite = false;
                    return;
                }
            }
        }
    }

    // bfs
    private void bfs(int[][] graph, int start){
        Deque<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.addLast(start);

        while(!q.isEmpty() && bipartite){
            int v = q.removeFirst();
            for (int w : graph[v]) {
                if (!visited[w]){
                    colors[w] = !colors[v];
                    visited[w] = true;
                    q.addLast(w);
                }else{
                    if (colors[w] == colors[v]){
                        bipartite = false;
                        return;
                    }
                }
            }
        }
    }
}
public class Main {
}
