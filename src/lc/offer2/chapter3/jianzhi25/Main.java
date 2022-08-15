package lc.offer2.chapter3.jianzhi25;

import lc.ListNode;

/**
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例1：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 限制：
 *
 * 0 <= 链表长度 <= 1000
 *
 * */

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // 结果链表的头节点
        ListNode head;
        // 比较两个链表的大小关系
        // 头节点指向值小的节点
        // 被指向的节点向后移动
        if (l1.val <= l2.val){
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }

        // 当前节点
        // 用于修改next
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val){
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 != null ){
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }

        return head;
    }
}


public class Main {
    public static void main(String[] args) {

    }
}
