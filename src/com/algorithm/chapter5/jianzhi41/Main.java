package com.algorithm.chapter5.jianzhi41;

import java.util.PriorityQueue;
import java.util.Queue;


/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例 1：
 *
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 * 示例 2：
 *
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 *
 *
 * 限制：
 *
 * 最多会对addNum、findMedian 进行 50000 次调用。
 *
 * */


class MedianFinder {
    // 左半部分，最大堆
    private Queue<Integer> maxHeap;
    // 右半部分，最小堆
    private Queue<Integer> minHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a,b) -> b-a);
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // 偶数位元素插入最大堆
        if (((maxHeap.size() + minHeap.size()) & 1) == 0){
            // 当前元素>=上界（最小堆顶元素）
            if (minHeap.size() > 0 && num >= minHeap.peek()){
                minHeap.add(num);
                num = minHeap.remove();
            }
            maxHeap.add(num);
        // 奇数位元素插入最小堆
        }else{
            // 当前元素小于等于下界（最大堆顶元素）
            if (maxHeap.size() > 0 && num <= maxHeap.peek()){
                maxHeap.add(num);
                num = maxHeap.remove();
            }
            minHeap.add(num);
        }
//        minHeap.add(num);
//        maxHeap.add(minHeap.remove());
//        if (maxHeap.size() - 1 > minHeap.size()){ // 调整平衡，大顶堆比小顶堆最多多一个
//            minHeap.add(maxHeap.remove());
//        }
    }

    public double findMedian() {
        int size = maxHeap.size() + minHeap.size();
        if (size == 0){
            return -1;
        }
        if ((size & 1) == 0)
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        else{
            // 第一个元素插入在最大堆，总数为奇数的时候，最大堆始终比最小堆多一个元素，所以中位数返回最大堆顶
            // 或者再加一个条件判断哪边的size更大，返回size更大的堆顶
            return maxHeap.peek();
        }
//        if (maxHeap.size() > minHeap.size())
//            return maxHeap.peek();
//        else{
//            return (maxHeap.peek() + minHeap.peek()) / 2.0;
//        }
    }
}


/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */


public class Main {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-5);
        System.out.println(medianFinder.findMedian());
    }
}
