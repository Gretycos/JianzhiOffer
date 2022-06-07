package com.algorithm.chapter6.jianzhi59_2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，
 * 要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value需要返回 -1
 *
 * 示例 1：
 *
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出:[null,null,null,2,1,2]
 * 示例 2：
 *
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出:[null,-1,-1]
 *
 *
 * 限制：
 *
 * 1 <= push_back,pop_front,max_value的总操作数<= 10000
 * 1 <= value <= 10^5
 *
 * */

class MaxQueue {

    private Deque<Integer> Q1;
    private Deque<Integer> Q2;

    public MaxQueue() {
        Q1 = new ArrayDeque<>();
        Q2 = new ArrayDeque<>();
    }

    public int max_value() {
        return Q1.isEmpty() ? -1 : Q2.getFirst();
    }

    public void push_back(int value) {
        Q1.addLast(value);
        // 把Q2队尾比当前元素小的元素移除，因为他们不会成为最大值
        // 队尾大于当前元素则当前元素有可能成为最大值
        while(!Q2.isEmpty() && Q2.getLast() < value){
            Q2.removeLast();
        }
        Q2.addLast(value);
    }

    public int pop_front() {
        if (Q1.isEmpty()) return -1;
        int val = Q1.removeFirst();
        if (Q2.getFirst() == val){
            Q2.removeFirst();
        }
        return val;
    }
}
public class Main {
    public static void main(String[] args) {
        MaxQueue q = new MaxQueue();
        System.out.println(q.max_value());
        q.push_back(1);
        q.push_back(2);
        q.push_back(2);
        System.out.println(q.pop_front());
        System.out.println(q.pop_front());
        System.out.println(q.max_value());
        q.push_back(3);
        System.out.println(q.max_value());
        System.out.println(q.pop_front());
        System.out.println(q.max_value());
    }
}
