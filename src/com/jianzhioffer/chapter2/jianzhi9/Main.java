package com.jianzhioffer.chapter2.jianzhi9;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 用两个栈实现一个队列。
 * 队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 * (若队列中没有元素，deleteHead操作返回 -1 )
 *
 *
 *
 * 示例 1：
 *
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 *
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 *
 * 1 <= values <= 10000
 * 最多会对appendTail、deleteHead 进行10000次调用
 *
 * */

class CQueue {
    Stack<Integer> S1; // 用于入队
    Stack<Integer> S2; // 用于出队

    public CQueue() {
        S1 = new Stack<>();
        S2 = new Stack<>();
    }

    public void appendTail(int value) {
        S1.push(value);
    }

    public int deleteHead() {
        if(!S2.isEmpty()) {
            return S2.pop();
        }
        while(!S1.isEmpty()){
            S2.push(S1.pop());
        }
        return S2.isEmpty() ? -1 : S2.pop();
    }
}

public class Main {
    public static void main(String[] args) {
        CQueue obj = new CQueue();
        ArrayList<Integer> result = new ArrayList<>();
        result.add(obj.deleteHead());
        obj.appendTail(5);
        obj.appendTail(2);
        result.add(obj.deleteHead());
        result.add(obj.deleteHead());
        for (Integer integer : result) {
            System.out.println(integer);
        }
    }
}
