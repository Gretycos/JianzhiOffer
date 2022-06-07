package com.algorithm.chapter3.jianzhi18;

import com.algorithm.ListNode;

class Solution {
    public ListNode deleteNode(ListNode head, int val) {
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
}
public class Main {
    public static void main(String[] args) {

    }
}
