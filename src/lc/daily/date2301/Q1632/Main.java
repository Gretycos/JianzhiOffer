package lc.daily.date2301.Q1632;

import java.util.*;

class UnionFind{
    private int[] f;

    public UnionFind(int n) {
        f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = i;
        }
    }

    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ){
            return;
        }
        f[rootQ] = rootP;
    }

    public int find(int p){
        if (f[p] != p){
            f[p] = find(f[p]);
        }
        return f[p];
    }
}
class Solution {
    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        UnionFind uf = new UnionFind(m * n); // 用一维下标进行连通

        // 连通行相同元素
        for (int i = 0; i < m; i++) {
            Map<Integer, List<Integer>> row = new HashMap<>();
            for (int j = 0; j < n; j++) {
                // 如果key不存在就new一个list，如果存在则返回list
                row.computeIfAbsent(matrix[i][j], key->new ArrayList<>()).add(i*n+j);
            }
            for (List<Integer> list : row.values()) {
                for (int k = 0; k < list.size() - 1; k++) {
                    uf.union(list.get(k), list.get(k+1));
                }
            }
        }

        // 连通列相同元素
        for (int j = 0; j < n; j++) {
            Map<Integer, List<Integer>> col = new HashMap<>();
            for (int i = 0; i < m; i++) {
                col.computeIfAbsent(matrix[i][j], key->new ArrayList<>()).add(i*n+j);
            }
            for (List<Integer> list : col.values()) {
                for (int k = 0; k < list.size() - 1; k++) {
                    uf.union(list.get(k), list.get(k+1));
                }
            }
        }

        // 图
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < m * n; i++) {
            graph.add(new ArrayList<>());
        }
        // 记录入度
        int[] inDegree = new int[m * n];
        // 行建图
        for (int i = 0; i < m; i++) {
            Integer[] idx = new Integer[n];
            for (int j = 0; j < n; j++) {
                idx[j] = j;
            }
            int row = i;
            Arrays.sort(idx,(a,b) -> matrix[row][a] - matrix[row][b]);
            for (int j = 0; j < n - 1; j++) {
                if (matrix[i][idx[j]] != matrix[i][idx[j+1]]){
                    // u --> v
                    int u = uf.find(i * n + idx[j]);
                    int v = uf.find(i * n + idx[j+1]);
                    inDegree[v] ++;
                    graph.get(u).add(v);
                }
            }
        }

        // 列建图
        for (int j = 0; j < n; j++) {
            Integer[] idx = new Integer[m];
            for (int i = 0; i < m; i++) {
                idx[i] = i;
            }
            int col = j;
            Arrays.sort(idx,(a,b) -> matrix[a][col] - matrix[b][col]);
            for (int i = 0; i < m - 1; i++) {
                if (matrix[idx[i]][j] != matrix[idx[i+1]][j]){
                    // u --> v
                    int u = uf.find(idx[i] * n + j);
                    int v = uf.find(idx[i+1] * n + j);
                    inDegree[v] ++;
                    graph.get(u).add(v);
                }
            }
        }

        // 拓扑排序
        int[] rank = new int[m * n];
        // 初始值为1
        Arrays.fill(rank, 1);
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < m * n; i++) {
            if (uf.find(i) == i && inDegree[i] == 0){
                q.addLast(i);
            }
        }
        while (!q.isEmpty()){
            int u = q.removeFirst();
            for (int v : graph.get(u)) {
                // 比较当前rank和邻接的rank+1，取大者
                rank[v] = Math.max(rank[v], rank[u] + 1);
                inDegree[v]--;
                if (inDegree[v] == 0){
                    q.addLast(v);
                }
            }
        }

        // 二维结果
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = rank[uf.find(i * n + j)];
            }
        }
        return res;
    }
}


public class Main {
}
