package lc.labuladong.DataStructure.graph.Q1135;
import lc.labuladong.DataStructure.graph.Prim;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class UnionFind{
    private int count;
    private int[] parent;
    public UnionFind(int n){
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ){
            return;
        }
        parent[rootQ] = rootP;
        count --;
    }

    private int find(int x){
        if (parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    public int count(){
        return count;
    }
}

class Solution{
    // Kruskal
    public int minimumCost(int n, int[][] connections) {
        // 顶点序号：1~n
        UnionFind uf = new UnionFind(n+1);
        // 按权重升序排序
        Arrays.sort(connections, (e1,e2) -> e1[2] - e2[2]);
        int mst = 0;
        for (int[] edge : connections) {
            int u = edge[0], v = edge[1], weight = edge[2];
            if (uf.connected(u, v)) continue;
            mst += weight;
            uf.union(u,v);
        }
        return uf.count() == 2 ? mst : -1;
    }

    // Prim
    public int minimumCost2(int n, int[][] connections) {
        List<int[]>[] graph = buildGraph(n, connections);
        Prim prim = new Prim(graph);
        if (!prim.allConnected()){
            return -1;
        }
        return prim.weightSum();
    }

    private List<int[]>[] buildGraph(int n, int[][] connections) {
        List<int[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : connections) {
            // 原本编号从1开始，要转成0开始
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            int weight = edge[2];
            graph[u].add(new int[]{u,v,weight});
            graph[v].add(new int[]{v,u,weight});
        }
        return graph;
    }
}
public class Main {
}
