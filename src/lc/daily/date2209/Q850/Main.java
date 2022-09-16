package lc.daily.date2209.Q850;

import java.util.*;

/**
 * 我们给出了一个（轴对齐的）二维矩形列表rectangles。
 * 对于rectangle[i] = [x1, y1, x2, y2]，
 * 其中（x1，y1）是矩形i左下角的坐标，
 * (xi1, yi1)是该矩形 左下角 的坐标，
 * (xi2, yi2)是该矩形右上角 的坐标。
 *
 * 计算平面中所有rectangles所覆盖的 总面积 。任何被两个或多个矩形覆盖的区域应只计算 一次 。
 *
 * 返回 总面积 。因为答案可能太大，返回10^9+ 7 的模。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
 * 输出：6
 * 解释：如图所示，三个矩形覆盖了总面积为6的区域。
 * 从(1,1)到(2,2)，绿色矩形和红色矩形重叠。
 * 从(1,0)到(2,3)，三个矩形都重叠。
 * 示例 2：
 *
 * 输入：rectangles = [[0,0,1000000000,1000000000]]
 * 输出：49
 * 解释：答案是 1018 对 (109 + 7) 取模的结果， 即 49 。
 *
 *
 * 提示：
 *
 * 1 <= rectangles.length <= 200
 * rectanges[i].length = 4
 * 0 <= xi1, yi1, xi2, yi2<= 109
 * 矩形叠加覆盖后的总面积不会超越2^63 - 1，这意味着可以用一个64 位有符号整数来保存面积结果。
 *
 * */
class Node{
    Node left, right; // 线段id区间左、右
    int len, coveredTimes; // 覆盖长度、被完全覆盖次数
}
class SegmentTree{
    private final int MAX;
    private final Node root;

    public SegmentTree(int max){
        root = new Node();
        MAX = max;
    }

    public int query(){
        return root.len;
    }

    public void update(int left, int right, int val, Map<Integer,Integer> idx2Y){
        update(root,0,MAX,left,right,val,idx2Y);
    }

    private void update(Node node, int start, int end,
                        int left, int right, int val, Map<Integer,Integer> idx2Y){
        // 动态开点
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        // 完全覆盖区间
        if (left <= start && right >= end){
            node.coveredTimes += val;
            pushUp(node,start,end,idx2Y);
            return;
        }
        int mid = start + (end - start) / 2;
        if (left <= mid){
            update(node.left,start,mid,left,right,val,idx2Y);
        }
        if (right > mid){
            update(node.right,mid+1,end,left,right,val,idx2Y);
        }
        pushUp(node,start,end,idx2Y);
    }

    private void pushUp(Node node, int start, int end, Map<Integer,Integer> idx2Y){
        if (node.coveredTimes > 0){
            // 线段长度为最后一个顶点id(=最后一个线段id+1)的值-第一个顶点id的值
            node.len = idx2Y.get(end+1) - idx2Y.get(start);
        }else if (start != end){ // 完全覆盖次数为0，但是不是叶节点
            node.len = node.left.len + node.right.len;
        }else {
            node.len = 0;
        }
    }
}

class Solution {
    private static final int MOD = (int) 1e9+7;
    private static final int IN = 1, OUT = -1;

    public int rectangleArea(int[][] rectangles) {
        // 按升序存储纵坐标并去重
        TreeSet<Integer> ySet = new TreeSet<>();

        // 存储离散化后的纵坐标与实际坐标的关系，用于维护线段树的区间
        Map<Integer,Integer> y2Idx = new HashMap<>(),
                             idx2Y = new HashMap<>();

        // 存储入边、出边、纵坐标
        List<int[]> edges = new ArrayList<>();
        for (int[] rect : rectangles) {
            // 入边[(x1,y1),(x1,y2)]
            // [x1,y1,y2,IN]
            edges.add(new int[]{rect[0],rect[1],rect[3],IN});
            // 出边[(x2,y1),(x2,y2)]
            // [x2,y1,y2,OUT]
            edges.add(new int[]{rect[2],rect[1],rect[3],OUT});
            // y1
            ySet.add(rect[1]);
            // y2
            ySet.add(rect[3]);
        }
        // 按横坐标x升序排序
        edges.sort((e1,e2) -> e1[0]-e2[0]);

        // 离散化纵坐标
        int idx = 0;
        for (int y : ySet) {
            y2Idx.put(y,idx);
            idx2Y.put(idx,y);
            idx++;
        }

        // 线段树
        SegmentTree segmentTree = new SegmentTree(idx);
        long res = 0;
        int n = edges.size();
        for (int i = 0; i < n - 1; i++){
            int[] curEdge = edges.get(i);
            int left = y2Idx.get(curEdge[1]), // y1
                right = y2Idx.get(curEdge[2]); // y2
            // 线段数量=顶点数量-1，例如：left=0,right=2,则只有2个长度为1的线段，线段id区间是[0,1(right-1)]
            segmentTree.update(left,right-1,curEdge[3],idx2Y);
            res += (long) segmentTree.query() * (edges.get(i+1)[0] - curEdge[0]);
        }

        return (int) (res % MOD);
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] rects = {{0,0,2,2},{1,0,2,3},{1,0,3,1}};
        System.out.println(solution.rectangleArea(rects));
    }
}
