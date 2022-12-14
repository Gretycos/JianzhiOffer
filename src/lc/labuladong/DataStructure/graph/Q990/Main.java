package lc.labuladong.DataStructure.graph.Q990;

class UnionFind{
    private int[] parent;
    public UnionFind(int n){
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
    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(26);
        for (String equation : equations) {
            if (equation.charAt(1) == '='){
                char x = equation.charAt(0);
                char y = equation.charAt(3);
                uf.union(x-'a',y-'a');
            }
        }

        for (String equation : equations) {
            if (equation.charAt(1) == '!'){
                char x = equation.charAt(0);
                char y = equation.charAt(3);
                // x与y连通了，说明x == y，因此发生逻辑冲突
                if (uf.connected(x-'a',y-'a')){
                    return false;
                }
            }
        }
        return true;
    }
}
public class Main {
}
