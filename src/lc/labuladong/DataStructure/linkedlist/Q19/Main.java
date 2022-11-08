package lc.labuladong.DataStructure.linkedlist.Q19;

import lc.ListNode;

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 使用虚拟头节点
        // 比如，若链表长为5，n == 5，倒数第n+1个节点是null，会产生分类讨论
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode x = getKthFromEnd(dummy, n + 1);
        x.next = x.next.next;
        return dummy.next;
    }

    private ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        ListNode slow = head;
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

}

public class Main {
}
