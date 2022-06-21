package com.jianzhioffer.chapter2.jianzhi6;

import com.ListNode;

import java.util.Stack;

class Solution {
    public int[] reversePrint2(ListNode head) {
        if (head == null){
            return new int[0];
        }
        Stack<Integer> stack = new Stack<>();
        for (ListNode p=head;p!=null;p=p.next){
            stack.push(p.val);
        }
        int[] result = new int[stack.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.pop();
        }
        return result;
    }
    public int[] reversePrint(ListNode head){
        int count = 0;
        ListNode p = head;
        while (p != null){
            count++;
            p = p.next;
        }
        int[] res = new int[count];
        int t = count - 1;
        p = head;
        while (p != null){
            res[t--] = p.val;
            p = p.next;
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        ListNode p1 = new ListNode(3);
        ListNode p2 = new ListNode(2);
        head.next=p1;
        p1.next=p2;
        for(int i:solution.reversePrint(head)){
            System.out.println(i);
        }
    }
}
