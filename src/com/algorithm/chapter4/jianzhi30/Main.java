package com.algorithm.chapter4.jianzhi30;

import java.util.*;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 * 调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 *
 *
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *
 * */

// 双向链表
class MinStack2 {
    private class Node{
        int val;
        int min; // 当前最小值
        Node next;
        Node prev;

        Node(int val, int min){
            this.val = val;
            this.min = min;
        }

        Node(int val, int min, Node prev){
            this.val = val;
            this.min = min;
            this.prev = prev;
        }
    }

    private Node head;

    /** initialize your data structure here. */
    public MinStack2() {

    }

    public void push(int x) {
        if (head == null){
            head = new Node(x,x); // 空链表，最小值就是本身
        }else{
            // 非空链表，最小值是新值与当前最小值的最小
            head.next = new Node(x, Math.min(x,head.min),head);
            head = head.next;
        }
    }

    public void pop() {
        if (head == null)
            return;
        head = head.prev;
    }

    public int top() {
        return head == null ? -1 : head.val;
    }

    public int min() {
        return head == null ? -1 : head.min;
    }
}

// 双栈
class MinStack {
    private Stack<Integer> stack; // 数据栈
    private Stack<Integer> stack2; // 最小值栈


    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        // 空或者比栈顶小才能压入最小值栈
        if (stack2.size() == 0 || x <= stack2.peek()){
            stack2.push(x);
        }
    }

    public void pop() {
        int x = stack.pop();
        // 如果弹出的元素和最小值栈顶元素相同，则弹出最小值栈顶
        if (x == stack2.peek()){
            stack2.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return stack2.peek();
    }
}


public class Main {
    public static void main(String[] args) {
        MinStack2 minStack2 = new MinStack2();
        minStack2.push(-2);
        minStack2.push(0);
        minStack2.push(-3);
        System.out.println(minStack2.min());
        minStack2.pop();
        System.out.println(minStack2.top());
        System.out.println(minStack2.min());
    }
}
