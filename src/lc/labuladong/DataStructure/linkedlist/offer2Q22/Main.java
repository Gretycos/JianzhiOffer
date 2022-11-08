package lc.labuladong.DataStructure.linkedlist.offer2Q22;

import lc.ListNode;

class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
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
