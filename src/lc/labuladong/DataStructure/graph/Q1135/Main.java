package lc.labuladong.DataStructure.graph.Q1135;

import java.util.Arrays;

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
}
public class Main {
}
