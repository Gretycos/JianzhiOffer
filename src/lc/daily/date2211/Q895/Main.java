package lc.daily.date2211.Q895;

import java.util.HashMap;
import java.util.Map;

class FreqStack {
    class Node{
        int val;
        Node next;
        Node pre;
        Node(int val){this.val = val;}
    }

    class CountNode{
        Node dummyHead;
        Node last;
        CountNode(){
            dummyHead = new Node(-1);
            last = dummyHead;
        }
    }

    private int maxCount = 0;
    private final CountNode[] countNodes; // 数组+双链表
    private final Map<Integer,Integer> val2Count; // 哈希表

    public FreqStack() {
        countNodes = new CountNode[20000];
        val2Count = new HashMap<>();
    }

    public void push(int val) {
        // 查询该元素的频数
        int curCount = val2Count.getOrDefault(val, 0);
        val2Count.put(val, ++curCount);
        // 如果超过了maxCount，则更新maxCount
        if (curCount> maxCount){
            maxCount = curCount;
        }
        // 当前count对应的双链表插入元素
        if (countNodes[curCount] == null){
            countNodes[curCount] = new CountNode();
        }
        CountNode cur = countNodes[curCount];
        cur.last.next = new Node(val);
        cur.last.next.pre = cur.last;
        cur.last = cur.last.next;

    }

    public int pop() {
        if (maxCount == 0) return -1;
        // 查询maxCount对应的双链表的最后一个元素
        CountNode curCountNode = countNodes[maxCount];
        int val = curCountNode.last.val;
        // 弹出该元素
        curCountNode.last = curCountNode.last.pre;
        curCountNode.last.next = null;
        // 元素的计数器-1
        val2Count.put(val, maxCount-1);
        // 如果maxCount对应的双链表空了，maxCount--
        if (curCountNode.dummyHead.next == null){
            maxCount --;
        }
        return val;
    }
}
public class Main {
    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
    }
}
