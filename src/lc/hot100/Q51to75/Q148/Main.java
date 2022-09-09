package lc.hot100.Q51to75.Q148;

import lc.ListNode;

/**
 * 给你链表的头结点head，请将其按 升序 排列并返回 排序后的链表 。
 *
 *
 *
 * 示例 1：
 *  4->2->1->3
 *  1->2->3->4
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 *
 * 示例 2：
 * -1->5->3->4->0
 * -1>0->3->4->5
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 *
 *
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 * 提示：
 *
 * 链表中节点的数目在范围[0, 5 * 10^4]内
 * -10^5<= Node.val <= 10^5
 *
 *
 * 进阶：你可以在O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 * */

class Solution {
    // 自底向上归并
    // T: O(nlogn) S:O(1)
    public ListNode sortList1(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        int len = 0;
        ListNode p = head;
        while (p != null){
            len ++;
            p = p.next;
        }
        for (int size = 1; size < len; size <<= 1){
            ListNode cur = dummyHead.next,
                     tail = dummyHead; // 用来寻找排序好的链表的尾部，并链接下一个排序好的链表
            while (cur != null){
                ListNode left = cur;
                ListNode right = cut(left,size);// 断链
                cur = cut(right,size); // cur的下一个位置
                tail.next = merge(left,right); // 合并两个链表
                while(tail.next != null){
                    tail = tail.next;
                }
            }
        }
        return dummyHead.next;
    }

    private ListNode cut(ListNode head, int size){
        ListNode p = head;
        while (--size > 0 && p != null){
            p = p.next;
        }
        if (p == null) return null;

        ListNode next = p.next;
        p.next = null;
        return next;
    }

    private ListNode merge(ListNode l1, ListNode l2){
        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
        while(l1 != null && l2 != null){
            if (l1.val < l2.val){
                p.next = l1;
                l1 = l1.next;
            }else{
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = l1 == null ? l2 : l1;
        return dummyHead.next;
    }

    // 自顶向下递归
    // T:O(nlogn) S:O(logn)
    public ListNode sortList(ListNode head) {
        return sortList(head,null);
    }

    private ListNode sortList(ListNode head, ListNode tail){
        if (head == null){
            return null;
        }
        if (head.next == tail){
            head.next = null;
            return head;
        }

        ListNode slow = head, fast = head;
        while (fast != tail){
            slow = slow.next;
            fast = fast.next;
            if (fast != tail){
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode l1 = sortList(head,mid),
                 l2 = sortList(mid,tail);
        return merge(l1,l2);
    }


}
public class Main {
}
