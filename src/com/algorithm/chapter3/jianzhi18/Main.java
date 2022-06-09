package com.algorithm.chapter3.jianzhi18;

import com.algorithm.ListNode;

class Solution {
    public ListNode deleteNode2(ListNode head, int val) {
        ListNode cur = head;
        ListNode prev = head;
        while (cur != null){
            if (cur.val == val){
                if (head == cur){
                    head = cur.next;
                    cur.next = null;
                } else {
                    prev.next = cur.next;
                    cur.next = null;
                    return head;
                }
            }else {
                prev = cur;
                cur = cur.next;
            }
        }
        return head;
    }

    public ListNode deleteNode(ListNode head, int val) {
        if(head == null) return null;
        if(head.val == val){
            return head.next;
        }

        ListNode p = head;
        while(p.next != null){
            if(p.next.val == val){
                p.next = p.next.next;
                break;
            }
            p = p.next;
        }

        return head;
    }
}
public class Main {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(9);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        Solution solution = new Solution();
        solution.deleteNode(head,9);

    }
}
