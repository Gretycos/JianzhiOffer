package lc.labuladong.DataStructure.graph.Q261;

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
        count--;
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
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // 属于同一个连通分量，说明成环了
            if (uf.connected(u,v)){
                return false;
            }
            uf.union(u,v);
        }
        return uf.count() == 1;
    }
}
public class Main {
}
