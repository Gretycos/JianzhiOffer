package lc.hot100.Q51to75.Q206;

import lc.ListNode;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 *
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：[2,1]
 *
 *
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 * */


class Solution {

    // 迭代
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // 递归
    private ListNode h;
    public ListNode reverseList2(ListNode head) {
        dfs(null,head);
        return h;
    }
    private void dfs(ListNode parent, ListNode cur){
        if (cur == null) return;
        if (cur.next == null){
            h = cur;
        }
        dfs(cur,cur.next);
        cur.next = parent;
    }
}
public class Main {
}
