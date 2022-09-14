package lc.hot100.Q51to75.Q155;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 *
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 *
 *
 * 示例 1:
 *
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 *
 * 提示：
 *
 * -2^31<= val <= 2^31- 1
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * push,pop,top, andgetMin最多被调用3 * 10^4次
 *
 * */
class MinStack {
    private final Deque<Integer> stack1,stack2;

    public MinStack() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    public void push(int val) {
        stack1.addLast(val);
        if (stack2.isEmpty() || val <= stack2.getLast()){
            stack2.addLast(val);
        }
    }

    public void pop() {
        int val = stack1.removeLast();
        if (val == stack2.getLast()){
            stack2.removeLast();
        }
    }

    public int top() {
        return stack1.getLast();
    }

    public int getMin() {
        return stack2.getLast();
    }
}
public class Main {
}
