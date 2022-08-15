package lc.offer2.chapter6.jianzhi54;

import lc.TreeNode;

/**
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 *
 *
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *   2
 * 输出: 4
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *
 *
 * 限制：
 *
 * 1 ≤ k ≤ 二叉搜索树元素个数
 *
 * */

class Solution {
    private int count;
    private int res;

    private void inOrder(TreeNode node, int k){
        if (count == k || node == null){
            return;
        }
        // 右-中-左，因为是第k大
        inOrder(node.right,k);
        if (++count == k){
            res = node.val;
            return;
        }
        inOrder(node.left,k);
    }

    public int kthLargest(TreeNode root, int k) {
        count = 0;
        res = -1;
        inOrder(root,k);
        return res;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode41 = new TreeNode(1);
        TreeNode treeNode31 = new TreeNode(2,treeNode41,null);
        TreeNode treeNode32 = new TreeNode(4);
        TreeNode treeNode21 = new TreeNode(3,treeNode31,treeNode32);
        TreeNode treeNode22 = new TreeNode(6);
        TreeNode root = new TreeNode(5,treeNode21,treeNode22);

        System.out.println(solution.kthLargest(root,1));
    }
}
