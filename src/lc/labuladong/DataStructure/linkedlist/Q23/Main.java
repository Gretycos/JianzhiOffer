package lc.labuladong.DataStructure.linkedlist.Q23;

import lc.ListNode;

import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        // 伪头
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        // 最小堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length,(a,b)->a.val - b.val);
        // 只用放入头节点
        for (ListNode head: lists){
            if (head != null){
                pq.add(head);
            }
        }
        while (!pq.isEmpty()){
            ListNode node = pq.remove();
            p.next = node;
            // 把后续节点插入堆中
            if (node.next != null){
                pq.add(node.next);
            }
            p = p.next;
        }
        return dummy.next;
    }
}

public class Main {
}
