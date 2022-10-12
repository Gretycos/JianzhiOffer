package lc.daily.date2210.Q817;

import lc.ListNode;

/**
 * 给定链表头结点head，该链表上的每个结点都有一个 唯一的整型值 。同时给定列表nums，该列表是上述链表中整型值的一个子集。
 *
 * 返回列表nums中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表nums中）构成的集合。
 *
 *
 *
 * 示例1：
 *  0->1->2->3
 *
 * 输入: head = [0,1,2,3], nums = [0,1,3]
 * 输出: 2
 * 解释: 链表中,0 和 1 是相连接的，且 nums 中不包含 2，所以 [0, 1] 是 nums 的一个组件，同理 [3] 也是一个组件，故返回 2。
 *
 *
 * 示例 2：
 * 0->1->2->3->4
 *
 * 输入: head = [0,1,2,3,4], nums = [0,3,1,4]
 * 输出: 2
 * 解释: 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
 *
 *
 * 提示：
 *
 * 链表中节点数为n
 * 1 <= n <= 10^4
 * 0 <= Node.val < n
 * Node.val中所有值 不同
 * 1 <= nums.length <= n
 * 0 <= nums[i] < n
 * nums 中所有值 不同
 *
 * */
class Solution {
    public int numComponents(ListNode head, int[] nums) {
        // 用数组作哈希表，更高效
        int[] set = new int[10001];
        for (int num : nums) {
            set[num] = 1;
        }
        int count = 0;
        for (ListNode p = head; p != null; p = p.next){
            if (set[p.val] != 0){
                count ++;
                do {
                    p = p.next;
                } while (p!= null && set[p.val] != 0);
                if (p == null) break;
            }
        }
        return count;
    }
}


public class Main {
}
