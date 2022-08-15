package lc.offer2.chapter4.jianzhi35;

import java.util.HashMap;
import java.util.Map;

/**
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
 * 还有一个 random 指针指向链表中的任意节点或者 null。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 *
 *
 *
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 *
 *
 *
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 *
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *
 *
 * 提示：
 *
 * -10000 <= Node.val <= 10000
 * Node.random为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 *
 * */

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val){
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution {

    // 空间O(1) 时间O(n)
    public Node copyRandomList2(Node head){
        if (head == null){
            return null;
        }

        Node p = head;

        // 在原链表内部复制节点，链接起来
        while (p != null){
            Node node = new Node(p.val);
            node.next = p.next;
            p.next = node;
            p = node.next;
        }

        p = head;
        Node newHead = head.next;
        Node newP;

        // 构建新节点的random指向
        while (p != null){
            newP = p.next;
            newP.random = p.random == null ? null : p.random.next;
            p = newP.next;
        }

        p = head;
        newP = newHead;

        // 拆分两个链表
        while(p != null){
            if (newP.next == null){
                p.next = null;
                break;
            }
            p.next = newP.next;
            p = newP.next;
            newP.next = p.next;
            newP = newP.next;
        }


        return newHead;
    }

    //  空间O(n) 时间O(n)
    public Node copyRandomList(Node head) {
        if (head == null){
            return null;
        }

        Map<Node,Node> nodeMap = new HashMap<>(); // 存放N与N'

        Node p = head;
        Node newHead = new Node(p.val);
        Node newP = newHead;

        nodeMap.put(p,newP);
        p = p.next;

        // 复制链表
        while (p != null){
            newP.next = new Node(p.val);
            newP = newP.next;

            nodeMap.put(p,newP);
            p = p.next;
        }

        p = head;
        newP = newHead;

        // 构建新表节点的random链接
        while (p != null){
            newP.random = nodeMap.get(p.random); // 要找S'只用找到S
            p = p.next;
            newP = newP.next;
        }

        return newHead;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Node head = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        node1.random = head;
        node2.random = node4;
        node3.random = node2;
        node4.random = head;
        solution.copyRandomList2(head);
    }
}
