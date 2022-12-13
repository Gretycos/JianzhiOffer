package lc.labuladong.DataStructure.graph.Q210;

import java.util.*;

class Solution {
    private List<Integer> postorder;
    private boolean hasCycle;
    private boolean[] visited, onPath;
    // dfs
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        postorder = new ArrayList<>();
        hasCycle = false;
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            traverse(graph,i);
        }

        if (hasCycle){
            return new int[]{};
        }

        Collections.reverse(postorder);
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = postorder.get(i);
        }
        return res;
    }

    private void traverse(List<Integer>[] graph, int s){
        if (onPath[s]){
            hasCycle = true;
        }
        if (visited[s] || hasCycle){
            return;
        }
        onPath[s] = true;
        visited[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        postorder.add(s);
        onPath[s] = false;
    }

    // bfs
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        int[] inDegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int to = edge[0];
            inDegree[to] ++;
        }

        // 初始化
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0){
                q.addLast(i);
            }
        }

        int[] res = new int[numCourses];
        int count = 0;
        while (!q.isEmpty()){
            int cur = q.removeFirst();
            res[count] = cur;
            count ++;
            for (int next : graph[cur]) {
                inDegree[next] --;
                if (inDegree[next] == 0){
                    q.addLast(next);
                }
            }
        }

        if (count != numCourses){
            return new int[]{};
        }
        return res;
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
