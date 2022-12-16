package lc.labuladong.DataStructure.graph.Q1584;

import lc.labuladong.DataStructure.graph.Prim;

import java.util.ArrayList;
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

class Solution {
    // Kruskal
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int xi = points[i][0], yi = points[i][1];
                int xj = points[j][0], yj = points[j][1];
                // 用坐标在points的索引表示点
                edges.add(new int[]{i, j, Math.abs(xi-xj) + Math.abs(yi-yj)});
            }
        }
        // 按权重升序排序
        edges.sort((e1,e2) -> e1[2] - e2[2]);
        int mst = 0;
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], weight = edge[2];
            if (uf.connected(u, v)) continue;
            mst += weight;
            uf.union(u, v);
        }
        return mst;
    }

    // Prim
    public int minCostConnectPoints2(int[][] points) {
        int n = points.length;
        List<int[]>[] graph = buildGraph(n, points);
        return new Prim(graph).weightSum();
    }

    private List<int[]>[] buildGraph(int n, int[][] points) {
        List<int[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int xi = points[i][0], yi = points[i][1];
                int xj = points[j][0], yj = points[j][1];
                int weight = Math.abs(xi-xj) + Math.abs(yi-yj);
                graph[i].add(new int[]{i,j,weight});
                graph[j].add(new int[]{j,i,weight});
            }
        }
        return graph;
    }
}

public class Main {
}
