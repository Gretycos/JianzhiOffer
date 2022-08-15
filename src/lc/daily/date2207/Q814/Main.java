package lc.daily.date2207.Q814;

import lc.TreeNode;

/**
 * 给你二叉树的根结点root，此外树的每个结点的值要么是 0 ，要么是 1 。
 *
 * 返回移除了所有不包含 1 的子树的原二叉树。
 *
 * 节点 node 的子树为 node 本身加上所有 node 的后代。
 *
 * 示例 1：
 *          1                    1
 *           \                    \
 *            0       ->           0
 *           / \                    \
 *          0   1                    1
 *
 * 输入：root = [1,null,0,0,1]
 * 输出：[1,null,0,null,1]
 * 解释：
 * 只有红色节点满足条件“所有不包含 1 的子树”。 右图为返回的答案。
 *
 * 示例 2：
 *            1            1
 *         /    \           \
 *        0      1   ->      1
 *       / \    / \           \
 *      0   0  0   1           1
 * 输入：root = [1,0,1,0,0,0,1]
 * 输出：[1,null,1,null,1]
 *
 * 示例 3：
 *            1                 1
 *         /    \             /  \
 *        1      0   ->      1    0
 *       / \    / \         / \    \
 *      1   1  0   1       1   1    1
 *     /
 *    0
 * 输入：root = [1,1,0,1,1,0,1,0]
 * 输出：[1,1,0,1,1,null,1]
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 200] 内
 * Node.val 为 0 或 1
 *
 *
 * */

class Solution {
    public TreeNode pruneTree(TreeNode root) {
        // 注意不能在原地让节点赋值为null，这样父节点的左右指针没有变化
        if (root == null){
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null){
            return null;
        }
        return root;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node21 = new TreeNode(0),
                node22 = new TreeNode(0),
                node11 = new TreeNode(0),
                root = new TreeNode(0);
        node11.left = node21;
        node11.right = node22;
        root.right = node11;
        solution.pruneTree(root);
    }
}
