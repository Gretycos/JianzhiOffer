package lc.hot100.Q51to75.Q234;

import lc.ListNode;

/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：false
 *
 * 提示：
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 *
 *
 * 进阶：你能否用O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * */

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head.next == null) return true;
        ListNode[] nodes = cutHalf(head);
        ListNode tail = nodes[0];
        ListNode head2 = nodes[1];
        head2 = reverse(head2);

        ListNode p = head;
        ListNode p2 = head2;
        boolean res = true;
        while (res && p2 != null){
            if (p.val != p2.val){
                res = false;
            }
            p = p.next;
            p2 = p2.next;
        }
        tail.next = reverse(head2);
        return res;
    }

    private ListNode[] cutHalf(ListNode head){
        ListNode prev = null;
        ListNode slow = head, fast = head;
        while (fast != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next;
            if (fast != null){
                fast = fast.next;
            }
        }
        prev.next = null;
        return new ListNode[]{prev,slow};
    }

    private ListNode reverse(ListNode head){
        ListNode prev = null, cur = head, next;
        while (cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
public class Main {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2),
                 node2 = new ListNode(2),
                 node3 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(head));

    }
}
