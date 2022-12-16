package lc.labuladong.DataStructure.graph;

import java.util.List;
import java.util.PriorityQueue;

public class Prim{
    private final PriorityQueue<int[]> pq; // 存储「横切边」的优先级队列
    private final boolean[] inMST; // 类似 visited 数组的作用，记录哪些节点已经成为最小生成树的一部分
    private int weightSum = 0; // 记录最小生成树的权重和
    // graph 是用邻接表表示的一幅图，
    // graph[s] 记录节点 s 所有相邻的边，
    // 三元组 int[]{from, to, weight} 表示一条边
    private final List<int[]>[] graph;

    public Prim(List<int[]>[] graph){
        this.graph = graph;
        pq = new PriorityQueue<>((e1,e2) -> e1[2] - e2[2]); // 按照边的权重从小到大排序
        // 图中有 n 个节点
        int n = graph.length;
        inMST = new boolean[n];

        // 随便从一个点开始切分都可以，我们不妨从节点 0 开始
        inMST[0] = true;
        cut(0);
        // 不断进行切分，向最小生成树中添加边
        while (!pq.isEmpty()){
            int[] edge = pq.remove();
            int to = edge[1], weight = edge[2];
            // 节点 to 已经在最小生成树中，跳过
            // 否则这条边会产生环
            if (inMST[to]) continue;
            // 将边 edge 加入最小生成树
            weightSum += weight;
            inMST[to] = true;
            // 节点 to 加入后，进行新一轮切分，会产生更多横切边
            cut(to);
        }
    }

    // 将 s 的横切边加入优先队列
    private void cut(int s){
        for (int[] edge : graph[s]) {
            int to = edge[1];
            // 相邻接点 to 已经在最小生成树中，跳过
            // 否则这条边会产生环
            if (inMST[to]) continue;
            // 加入横切边队列
            pq.add(edge);
        }
    }

    // 最小生成树的权重和
    public int weightSum(){
        return weightSum;
    }

    // 判断最小生成树是否包含图中的所有节点
    public boolean allConnected(){
        for (boolean in : inMST) {
            if (!in) return false;
        }
        return true;
    }

}
