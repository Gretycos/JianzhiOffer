package lc.daily.date2212.Q1697;

import java.util.Arrays;

class UnionFind{
    private int[] f;

    public UnionFind(int n) {
        f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = i;
        }
    }

    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        f[rootX] = rootY;
    }
    public int find(int x){
        // 路经压缩
        if (f[x] != x){
            f[x] = find(f[x]);
        }
        return f[x];
    }

    public boolean isConnected(int x, int y){
        return find(x) == find(y);
    }
}
class Solution {
    // 排序的原因
    // 如果limit是非递减的，那么上一次查询的边都是满足limit要求的，只用将剩余的长度小于limit的边加入并查集即可
    // 因此，要把edgeList按weight升序排序，把queries按limit升序排序
    // 这样就可以根据单调性利用双指针算法来处理每条询问
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        // edgeList按weight升序排序
        Arrays.sort(edgeList, (e1, e2) -> e1[2] - e2[2]);

        // queries的下标按queries的limit升序排序
        // 为了按queries的顺序得出结果，不排序queries，而排序它的下标
        Integer[] qIdx = new Integer[queries.length];
        for (int i = 0; i < qIdx.length; i++) {
            qIdx[i] = i;
        }
        Arrays.sort(qIdx, (idx1, idx2) -> queries[idx1][2] - queries[idx2][2]);

        UnionFind uf = new UnionFind(n); // 用节点数n初始化并查集

        boolean[] res = new boolean[queries.length];
        int e = 0; // edgeList的指针
        for (int idx : qIdx) {
            // weight < limit
            while (e < edgeList.length && edgeList[e][2] < queries[idx][2]){
                uf.union(edgeList[e][0],edgeList[e][1]);
                e ++;
            }
            res[idx] = uf.isConnected(queries[idx][0], queries[idx][1]);
        }
        return res;
    }
}

public class Main {
}
