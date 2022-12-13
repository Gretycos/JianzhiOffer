package lc.labuladong.DataStructure.graph.Q207;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution{
    private boolean[] visited;
    private boolean[] onPath;
    private boolean hasCycle;
    // dfs
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];

        hasCycle = false;
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        return !hasCycle;
    }

    private void traverse(List<Integer>[] graph, int s){
        if (onPath[s]){
            hasCycle = true;
        }

        if (visited[s] || hasCycle){
            return;
        }

        visited[s] = true;
        onPath[s] = true; // 加入
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        onPath[s] = false; // 撤销
    }

    // bfs
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        int[] inDegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int to = edge[0];
            inDegree[to]++;
        }
        // 初始化
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            // 存放入度为0的节点作为起点
            if (inDegree[i] == 0){
                q.addLast(i);
            }
        }

        int count = 0; // 遍历的节点个数
        while (!q.isEmpty()){
            int cur = q.removeFirst();
            count ++;
            for (int next : graph[cur]) {
                inDegree[next]--;
                // 入度为0，说明不存在依赖，可以加入队列
                if (inDegree[next] == 0){
                    q.addLast(next);
                }
            }
        }
        // 如果都被遍历过就不存在环，返回true，反之返回false
        return count == numCourses;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites){
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            graph[from].add(to);
        }
        return graph;
    }
}
public class Main {
}
