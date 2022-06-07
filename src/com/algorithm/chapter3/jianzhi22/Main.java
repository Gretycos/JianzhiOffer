package com.algorithm.chapter3.jianzhi22;


import com.algorithm.ListNode;

class Solution {
    public ListNode getKthFromEnd2(ListNode head, int k) {
        // 正数 = 总数+1-倒数
        int n = 0;
        ListNode p = head;

        while(p!=null){
            p = p.next;
            n ++;
        }

        int i = 1;
        int x = n + 1 - k;
        p = head;
        while (i < x){
            p=p.next;
            i++;
        }

        return p;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head, slow = head;
        while(k -- > 0 && fast != null){
            fast = fast.next;
        }
        if(k >= 0) return null;
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode node = solution.getKthFromEnd(node1,5);
        while(node!=null){
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
}
