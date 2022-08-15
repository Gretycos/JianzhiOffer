package lc.offer2.chapter3.jianzhi23;

import lc.ListNode;

class Solution{
    private ListNode findMeetingNode(ListNode head){
        if (head == null){
            return null;
        }
        ListNode slow = head.next;
        if (slow == null){
            return null;
        }

        ListNode fast = slow.next;
        while (fast != null && slow != null){
            if (fast == slow){
                return fast;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast != null){
                fast = fast.next; // 快节点多走一步
            }
        }

        return null;
    }

    public ListNode entryNodeOfLoop(ListNode head){
        ListNode meetingNode = findMeetingNode(head);
        if (meetingNode == null) {
            return null;
        }

        // 计算环的节点数
        int nodesInLoop = 1;
        ListNode p = meetingNode;
        while(p.next != meetingNode){
            nodesInLoop ++;
            p = p.next;
        }

        // 寻找入口
        p = head;
        // 第一个指针先走环中节点数的长度
        for (int i = 0; i < nodesInLoop; i++) {
            p = p.next;
        }
        ListNode q = head;
        // 第二个指针再开始出发，直到相遇
        while(p != q){
            p = p.next;
            q = q.next;
        }

        return p;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
