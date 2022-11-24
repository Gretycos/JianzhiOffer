package lc.labuladong.DataStructure.tree.Q116;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        traverse(root.left,root.right);
        return root;
    }

    private void traverse(Node node1, Node node2){
        if (node1 == null || node2 == null) return;

        // 前序位置
        node1.next = node2;
        traverse(node1.left,node1.right);
        traverse(node2.left,node2.right);
        traverse(node1.right,node2.left);
    }
}

public class Main {
}
