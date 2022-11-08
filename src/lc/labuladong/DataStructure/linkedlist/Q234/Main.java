package lc.labuladong.DataStructure.linkedlist.Q234;

import lc.ListNode;

class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        ListNode preSlow = null; // 用于恢复
        while (fast != null && fast.next != null){
            preSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 奇数情况下需要避开最中间的节点
        if (fast != null) {
            preSlow = slow;
            slow = slow.next;
        }
        ListNode left = head, right = reverse(slow);
        ListNode lastNode = right; // 用于恢复

        while(right != null){
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }
        preSlow.next = reverse(lastNode);
        return true;
    }

    private ListNode reverse(ListNode head){
        ListNode pre = null, cur = head, nxt;
        while (cur != null){
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }



}
public class Main {
}
