package lc.labuladong.DataStructure.graph.Q1514;

import java.util.*;

class State{
    int id;
    double proFromStart;

    public State(int id, double proFromStart) {
        this.id = id;
        this.proFromStart = proFromStart;
    }
}
class Node{
    int id;
    double weight;

    public Node(int to, double weight) {
        this.id = to;
        this.weight = weight;
    }
}
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Node>[] graph = buildGraph(n, edges, succProb);
        return dijkstra(start,end,graph);
    }

    private double dijkstra(int start, int end, List<Node>[] graph){
        double[] probTo = new double[graph.length];
        // 取不到的值
        Arrays.fill(probTo, -1);
        // base
        probTo[start] = 1;

        PriorityQueue<State> pq = new PriorityQueue<>(
                (s1,s2) -> Double.compare(s2.proFromStart, s1.proFromStart)
        );

        pq.add(new State(start,1));
        while (!pq.isEmpty()){
            State curState = pq.remove();
            int curId = curState.id;
            double curProbFromStart = curState.proFromStart;
            // 找到终点
            if (curId == end) return curProbFromStart;
            // 已经存在概率更大的路径
            if (curProbFromStart < probTo[curId]) continue;
            for (Node neighbor : graph[curId]) {
                int nextId = neighbor.id;
                double probToNext = neighbor.weight * probTo[curId];
                if (probTo[nextId] < probToNext){
                    probTo[nextId] = probToNext;
                    pq.add(new State(nextId, probToNext));
                }
            }
        }
        // 不可达
        return 0.0;
    }

    private List<Node>[] buildGraph(int n, int[][] edges, double[] succProb){
        List<Node>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0], to = edges[i][1];
            double weight = succProb[i];
            graph[from].add(new Node(to, weight));
            graph[to].add(new Node(from, weight));
        }
        return graph;
    }
}

public class Main {
}
