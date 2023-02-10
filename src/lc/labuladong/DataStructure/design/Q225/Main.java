package lc.labuladong.DataStructure.design.Q225;

import java.util.ArrayDeque;
import java.util.Deque;

class MyStack {
    private final Deque<Integer> queue;
    private int topE; // 栈顶
    public MyStack() {
        queue = new ArrayDeque<>();
    }

    public void push(int x) {
        queue.addLast(x);
        topE = x;
    }

    public int pop() {
        int size = queue.size();
        while (size > 2){
            queue.addLast(queue.removeFirst());
            size--;
        }
        topE = queue.removeFirst();
        queue.addLast(topE);
        return queue.removeFirst();
    }

    public int top() {
        return topE;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

public class Main {
}
