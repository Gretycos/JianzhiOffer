package lc.hot100.Q1to25.Q19;

import lc.ListNode;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例 1：
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 *
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * */

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        while( n > 0 ){
            fast = fast.next;
            n --;
        }
        ListNode slow = head, prev = null;
        while(fast != null){
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if (prev == null){
            head = slow.next;
        } else {
            prev.next = slow.next;
        }

        return head;
    }
}

public class Main {
}
