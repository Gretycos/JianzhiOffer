package lc.hot100.Q114;

import lc.TreeNode;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 *
 * 示例 1：
 *                  1                   1
 *               /    \                  \
 *              2      5                  2
 *             / \      \                  \
 *            3   4      6                  3
 *                                           \
 *                                            4
 *                                             \
 *                                              5
 *                                               \
 *                                                6
 *
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [0]
 * 输出：[0]
 *
 *
 * 提示：
 *
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 *
 * */

class Solution {
    private TreeNode last;
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = last;
        root.left = null;
        last = root;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node21 = new TreeNode(3);
        TreeNode node22 = new TreeNode(4);
        TreeNode node23 = new TreeNode(6);
        TreeNode node11 = new TreeNode(2);
        TreeNode node12 = new TreeNode(5);
        TreeNode root = new TreeNode(1);
        node11.left = node21;
        node11.right = node22;
        node12.right = node23;
        root.left = node11;
        root.right = node12;
        solution.flatten(root);
    }
}
