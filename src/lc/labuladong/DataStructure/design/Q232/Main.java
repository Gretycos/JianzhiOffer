package lc.labuladong.DataStructure.design.Q232;

import java.util.ArrayDeque;
import java.util.Deque;

class MyQueue {
    private Deque<Integer> stack1, stack2;
    public MyQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    public void push(int x) {
        stack1.addLast(x);
    }

    public int pop() {
        peek();
        return stack2.removeLast();
    }

    public int peek() {
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.addLast(stack1.removeLast());
            }
        }
        return stack2.getLast();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}

public class Main {
}
