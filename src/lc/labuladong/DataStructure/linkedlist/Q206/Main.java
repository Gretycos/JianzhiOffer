package lc.labuladong.DataStructure.linkedlist.Q206;

import lc.ListNode;

class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        // 记录反转后的头节点
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}

public class Main {
}
