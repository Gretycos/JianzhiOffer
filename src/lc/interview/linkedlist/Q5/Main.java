package lc.interview.linkedlist.Q5;

import lc.ListNode;

/**
 * @Author Tsong
 * @Date 2023/7/2 13:26
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(-1);
        ListNode p1 = l1, p2 = l2, p3 = l3;
        int carry = 0;
        while (p1 != null || p2 != null || carry != 0){
            int sum = (p1 == null ? 0 : p1.val) + (p2 == null ? 0 : p2.val) + carry;
            carry = sum / 10;
            p3.next = new ListNode(sum % 10);
            p3 = p3.next;
            if (p1 != null){
                p1 = p1.next;
            }
            if (p2 != null){
                p2 = p2.next;
            }
        }
        return l3.next;
    }
}

public class Main {
    public static void main(String[] args) {
    }
}
