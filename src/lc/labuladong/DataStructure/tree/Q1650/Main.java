package lc.labuladong.DataStructure.tree.Q1650;

class Node{
    int val;
    Node left;
    Node right;
    Node parent;
}
class Solution{
    public Node lowestCommonAncestor(Node p, Node q) {
        // 转化成单链表求交点问题
        Node a = p, b = q;
        while (a != b){
            if (a == null){
                a = q;
            } else {
                a = a.parent;
            }
            if (b == null){
                b = p;
            } else {
                b = b.parent;
            }
        }
        return a;
    }
}
public class Main {
}
