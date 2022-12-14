package lc.labuladong.DataStructure.graph.Q130;

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
    // 实际上这道题用并查集很慢，但是这里只是为了体验并查集的过程
    // 更快的做法：从4个边缘的O开始dfs，改成别的符号#
    // 然后扫描棋盘把O改成X
    // 最后把#改成O
    public void solve(char[][] board) {
        if (board.length == 0) return;

        int m = board.length, n = board[0].length;
        UnionFind uf = new UnionFind(m * n + 1);
        int dummy = m * n; // 终极节点的序号
        // 首列和末列的O
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O'){
                uf.union(i * n, dummy);
            }
            if (board[i][n-1] == 'O'){
                uf.union(i * n + (n - 1), dummy);
            }
        }
        // 首行和尾行的O
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O'){
                uf.union(j, dummy);
            }
            if (board[m-1][j] == 'O'){
                uf.union((m-1) * n + j, dummy);
            }
        }

        // 连通内部的O
        int[][] dir = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O'){
                    for (int d = 0; d < 4; d++) {
                        int x = i + dir[d][0];
                        int y = j + dir[d][1];
                        if (board[x][y] == 'O'){
                            uf.union(x * n + y, i * n + j);
                        }
                    }
                }
            }
        }

        // 替换所有不和dummy连通的O
        for (int i = 1; i < m-1; i++) {
            for (int j = 1; j < n-1; j++) {
                if (!uf.connected(i * n + j, dummy)){
                    board[i][j] = 'X';
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {
                {'O','O','O'},
                {'O','O','O'},
                {'O','O','O'}
        };
        solution.solve(board);

    }
}
