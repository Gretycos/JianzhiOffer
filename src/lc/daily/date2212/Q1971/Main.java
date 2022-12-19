package lc.daily.date2212.Q1971;

class UnionFind{
    private final int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootQ == rootP) return;
        parent[rootQ] = rootP;
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
}

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            uf.union(u,v);
        }
        return uf.connected(source,destination);
    }
}

public class Main {
}
