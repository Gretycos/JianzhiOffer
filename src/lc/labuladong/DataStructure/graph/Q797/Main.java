package lc.labuladong.DataStructure.graph.Q797;

import java.util.LinkedList;
import java.util.List;

class Solution {
    private List<List<Integer>> res;
    private int n;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        res = new LinkedList<>();
        n = graph.length;
        traverse(graph, 0, new LinkedList<>());
        return res;
    }

    private void traverse(int[][] graph, int s, LinkedList<Integer> path){
        // 添加s
        path.addLast(s);
        // 到达终点
        if (s == n - 1){
            res.add(new LinkedList<>(path));
            path.removeLast();
            return;
        }
        // 遍历相邻节点
        for (int v : graph[s]) {
            traverse(graph, v, path);
        }
        // 移除s
        path.removeLast();
    }
}

public class Main {
}
