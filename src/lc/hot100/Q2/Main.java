package lc.hot100.Q2;
import lc.ListNode;


/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 *
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 *
 * 提示：
 *
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 **/

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2; // 两个链表的指针
        ListNode l3 = null, p3 = null; // l3的链表与指针
        // 进位
        int carry = 0;

        // 只要有一个不为空就可以继续加法
        while(p1 != null || p2 != null || carry != 0){
            // 把空的节点的值设为0
            int val1 = p1 == null ? 0 : p1.val;
            int val2 = p2 == null ? 0 : p2.val;
            // 带上进位求和
            int sum = val1 + val2 + carry;

            carry = sum / 10;

            // 头节点处理
            if (l3 == null){
                l3 = new ListNode(sum % 10);
                p3 = l3;
            } else {
                p3.next = new ListNode(sum % 10);
                p3 = p3.next;
            }

            // 移动p1 p2
            if (p1 != null){
                p1 = p1.next;
            }
            if (p2 != null){
                p2 = p2.next;
            }

        }
        return l3;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(9);
        ListNode l12 = new ListNode(9);
        ListNode l13 = new ListNode(9);
        l1.next = l12;
        l12.next = l13;

        ListNode l2 = new ListNode(9);
        ListNode l22 = new ListNode(9);
        l2.next = l22;

        System.out.println(solution.addTwoNumbers(l1,l2));
    }
}
