package lc.offer2.chapter6.jianzhi55_2;


import lc.TreeNode;

/**
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 *
 *  
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回false 。
 *
 *
 *
 * 限制：
 *
 * 0 <= 树的结点个数 <= 10000
 *
 * */

class Solution {

    private int getHeight2(TreeNode node){
        // 简化
        return node == null ?  0 : Math.max(getHeight2(node.left),getHeight2(node.right)) + 1;
    }

    // 最坏: 时间O(n^2), 平均: O(nlogn)
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(getHeight2(root.left) - getHeight2(root.right)) <= 1) {
            return isBalanced2(root.left) && isBalanced2(root.right);
        }
        return false;
    }

    private int getHeight(TreeNode node){
        if (node == null){
            return 0;
        }
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        if (left == -1 || right == -1 || Math.abs(left-right) > 1){
            return -1;
        }else{
            return Math.max(left,right) + 1;
        }
    }

    // 时间: O(n)
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) >= 0;
    }
}
public class Main {
    public static void main(String[] args) {

    }
}
