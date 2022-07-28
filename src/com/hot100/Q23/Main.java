package com.hot100.Q23;

import com.ListNode;

import java.util.*;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 *
 *
 * 提示：
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 * */

class Solution {
    // n: 链表平均长度，k: 链表数，m个不同的值
    // 排序的哈希表
    // Time:O(nk*logm) Space: O(nk)
    public ListNode mergeKLists(ListNode[] lists) {
        Map<Integer, List<ListNode>> map = new TreeMap<>();
        for(ListNode l : lists){
            ListNode p = l;
            while(p != null){
                map.putIfAbsent(p.val,new ArrayList<>());
                map.get(p.val).add(p);
                p = p.next;
            }
        }
        ListNode head = new ListNode(0),
                 tail = head;
        for(List<ListNode> l : map.values()){
            for(ListNode p: l){
                tail.next = p;
                tail = tail.next;
            }
        }
        return head.next;
    }
    // 优先队列
    // Time:O(nk*lognk) Space: O(nk)
    public ListNode mergeKLists2(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((node1,node2) -> node1.val - node2.val);
        for (ListNode l : lists){
            ListNode p = l;
            while(p != null){

                p = p.next;
            }
        }
        ListNode head = new ListNode(0),
                 tail = head;
        while (!pq.isEmpty()){
            tail.next = pq.poll();
            tail = tail.next;
        }
        // 堆排序不稳定，最后一个节点的next置空，否则可能会成环
        tail.next = null;
        return head.next;
    }

    // 归并排序
    // Time:O(nk*logk) Space: O(logk)
    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists.length == 0) return  null;
        return mergeKLists(lists,0,lists.length-1);
    }

    // 划分
    private ListNode mergeKLists(ListNode[] lists, int left, int right){
        if (left == right){
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        return merge(mergeKLists(lists,left,mid), mergeKLists(lists,mid+1,right));
    }

    // 两两合并
    private ListNode merge(ListNode l1, ListNode l2){
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = new ListNode(0),
                 tail = head;
        while(l1 != null && l2 != null){
            if (l1.val < l2.val){
                tail.next = l1;
                l1 = l1.next;
            }else{
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = l1 == null? l2: l1;
        return head.next;
    }

}
public class Main {
}
