package com.jianzhioffer.chapter4.jianzhi36;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
 * 要求不能创建任何新的节点，只能调整树中节点指针的指向。
 *
 *
 *
 * 为了让您更好地理解问题，以下面的二叉搜索树为例：
 *
 *           4
 *          / \
 *         2   5
 *        / \
 *       1   3
 *
 *
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。
 * 对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 *
 * 下图展示了上面的二叉搜索树转化成的链表。
 * “head” 表示指向链表中有最小元素的节点。
 *
 *    head
 *      ↓
 *    ⇄ 1 ⇄ 2 ⇄ 3 ⇄ 4 ⇄ 5 ⇄
 *    ⎣___________________⎦
 *
 *
 *
 * 特别地，我们希望可以就地完成转换操作。
 * 当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。
 * 还需要返回链表中的第一个节点的指针。
 *
 *
 *
 * */

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int val) { this.val=val; }

    public Node(int val, Node left, Node right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {

    private Node last; // 循环双向链表的最后一个节点

    private void convert(Node node){
        if (node == null){
            return;
        }

        // 中序遍历
        // 因为按照从小到大的顺序
        // 左子树
        if (node.left != null){
            convert(node.left);
        }

        // 根节点
        // 链接当前节点和循环双向链表的最后一个节点
        node.left = last;
        if (last != null){
            last.right = node;
        }
        // 循环双向链表最后一个节点指向当前
        last = node;

        // 右子树
        if (node.right != null){
            convert(node.right);
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null){
            return null;
        }
        // 寻找头节点
        Node p = root;
        while(p.left != null){
            p = p.left;
        }
        Node head = p;

        // 转换
        last = null;
        convert(root);

        // 链接头节点和尾节点
        head.left = last;
        last.right = head;

        return head;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Node node21 = new Node(1);
        Node node22 = new Node(3);
        Node node11 = new Node(2,node21,node22);
        Node node12 = new Node(5);
        Node root = new Node(4,node11,node12);

        Node p = solution.treeToDoublyList(root);
        Node head = p;
        do{
            System.out.print(p.val + " ");
            p = p.right;
        } while (p != head);
    }
}
