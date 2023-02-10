package lc.labuladong.DataStructure.design.Q239;

import java.util.*;
/* 单调队列的通用实现，可以高效维护最大值和最小值 */
interface MonotonicQue<E extends Comparable<E>> {

    // 标准队列 API，向队尾加入元素
    public void push(E elem);

    // 标准队列 API，从队头弹出元素，符合先进先出的顺序
    public E pop();

    // 标准队列 API，返回队列中的元素个数
    public int size();

    // 单调队列特有 API，O(1) 时间计算队列中元素的最大值
    public E max();

    // 单调队列特有 API，O(1) 时间计算队列中元素的最小值
    public E min();
}

class MonotonicQueue{
    private final Deque<Integer> maxQ;

    public MonotonicQueue() {
        maxQ = new LinkedList<>();
    }

    public void push(int x){
        while (!maxQ.isEmpty() && maxQ.getLast() < x){
            maxQ.removeLast();
        }
        maxQ.addLast(x);
    }

    public int max(){
        return maxQ.getFirst();
    }

    public void pop(int x){
        if (x == maxQ.getFirst()){
            maxQ.removeFirst();
        }
    }
}
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1){
                window.push(nums[i]);
            } else {
                window.push(nums[i]);
                res.add(window.max());
                window.pop(nums[i - k + 1]);
            }
        }

        int[] arr = new int[res.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }
}
public class Main {
}
