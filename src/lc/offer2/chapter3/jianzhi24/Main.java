package lc.offer2.chapter3.jianzhi24;

import lc.ListNode;

/**
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 *
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 * */

class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null){
            return head;
        }

        ListNode pre = head;
        ListNode cur = head;
        ListNode post = head.next;
        head.next = null;

        while (post != null){
            cur = post;
            post = post.next;
            cur.next = pre;
            pre = cur;
        }

        return cur;
    }
}
public class Main {
    public static void main(String[] args) {

    }
}
