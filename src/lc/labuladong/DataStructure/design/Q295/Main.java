package lc.labuladong.DataStructure.design.Q295;

import java.util.PriorityQueue;

class MedianFinder {
    private final PriorityQueue<Integer> small, large;

    public MedianFinder() {
        // 大顶堆，存放较小元素
        small = new PriorityQueue<>((a, b) -> b - a);
        // 小顶堆，存放较大元素
        large = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (small.size() >= large.size()){
            small.add(num);
            large.add(small.remove());
        } else {
            large.add(num);
            small.add(large.remove());
        }
    }

    public double findMedian() {
        if (small.size() > large.size()){
            return small.peek();
        } else if (large.size() > small.size()){
            return large.peek();
        }
        return (small.peek() + large.peek()) / 2.0;
    }
}

public class Main {
}
