package lc.hot100.Q76toQ100.Q399;

/**
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
 * 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
 * 每个 Ai 或 Bi 是一个表示单个变量的字符串。
 *
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，
 * 请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 *
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 * 如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 *
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0],
 *      queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 *
 * 示例 2：
 *
 * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0],
 *      queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * 输出：[3.75000,0.40000,5.00000,0.20000]
 *
 *
 * 示例 3：
 *
 * 输入：equations = [["a","b"]], values = [0.5],
 *      queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * 输出：[0.50000,2.00000,-1.00000,-1.00000]
 *
 *
 * 提示：
 *
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 *
 * */

import java.util.*;

class UnionFind{
    private int[] f; // 并查集，存储父节点
    private double[] w; // 权值定义为与父节点的比值

    public UnionFind(int n) {
        f = new int[n];
        w = new double[n];
        for (int i = 0; i < n; i++) {
            f[i] = i; // 父节点指向自己
            w[i] = 1.0; // 与自己的比值是1.0
        }
    }

    public void union(int x, int y, double val){
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY){
            return;
        }
        f[rootX] = rootY;
        // 因为x->y->f(y)路径乘积 == x->f(x)->f(y)路径乘积
        w[rootX] = val * w[y] / w[x];
    }

    /**
     * 查找 + 路径压缩
     * */
    private int find(int x){
        // x的父节点如果不是x，就继续寻找x的父节点的父节点，直到根节点
        if (f[x] != x){
            int parent = f[x];
            // 把x的父节点指向根节点
            f[x] = find(f[x]);
            // 权值是当前权值与原本父节点权值的乘积
            w[x] *= w[parent];
        }
        return f[x];
    }

    // 是否在同一集合中
    public double isConnected(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY){
            return w[x] / w[y];
        }else{
            return -1.0;
        }
    }
}
class Solution {
    // 带权值的并查集
    // T: O((E + Q)logA)  E:|equations|, Q:|queries|, A: equations的不同的变量数
    //  构建：O(ElogA)
    //  查询：O(QlogA)
    //  路径压缩：O(logA)
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> vars2Id = new HashMap<>();
        int sizeOfVars = 0;
        for (List<String> equation : equations) {
            String var1 = equation.get(0), var2 = equation.get(1);
            if (!vars2Id.containsKey(var1)){
                vars2Id.put(var1,sizeOfVars++);
            }
            if (!vars2Id.containsKey(var2)){
                vars2Id.put(var2,sizeOfVars++);
            }
        }

        UnionFind unionFind = new UnionFind(sizeOfVars);
        // 合并
        for (int i = 0; i < equations.size(); i++) {
            int var1 = vars2Id.get(equations.get(i).get(0)),
                var2 = vars2Id.get(equations.get(i).get(1));
            unionFind.union(var1,var2,values[i]);
        }

        // 查询
        double[] res = new double[queries.size()];
        int t = 0;
        for (List<String> query : queries) {
            double r = -1;
            String var1 = query.get(0), var2 = query.get(1);
            if (vars2Id.containsKey(var1) && vars2Id.containsKey(var2)){
                int v1 = vars2Id.get(var1), v2 = vars2Id.get(var2);
                // 是否在同一集合中
                r = unionFind.isConnected(v1,v2);
            }
            res[t++] = r;
        }
        return res;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("x1","x2"),
                Arrays.asList("x2","x3"),
                Arrays.asList("x3","x4"),
                Arrays.asList("x4","x5"));
        double[] values = {3.0,4.0,5.0,6.0};
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("x1","x5"),
                Arrays.asList("x5","x2"),
                Arrays.asList("x2","x4"),
                Arrays.asList("x2","x2"),
                Arrays.asList("x2","x9"),
                Arrays.asList("x9","x9"));
        System.out.println(Arrays.toString(solution.calcEquation(equations, values, queries)));
    }
}
