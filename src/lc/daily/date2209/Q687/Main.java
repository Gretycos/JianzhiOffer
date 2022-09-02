package lc.daily.date2209.Q687;

import lc.TreeNode;

/**
 * 给定一个二叉树的root，返回最长的路径的长度 ，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 *
 * 两个节点之间的路径长度由它们之间的边数表示。
 *
 *
 *
 * 示例 1:
 *              5
 *            /   \
 *           4     5
 *          / \     \
 *         1   1     5
 *
 *
 * 输入：root = [5,4,5,1,1,5]
 * 输出：2
 *
 *
 * 示例 2:
 *              1
 *            /   \
 *           4     5
 *          / \     \
 *         1   1     5
 *
 *
 * 输入：root = [1,4,5,4,4,5]
 * 输出：2
 *
 *
 * 提示:
 *
 * 树的节点数的范围是[0, 10^4]
 * -1000 <= Node.val <= 1000
 * 树的深度将不超过 1000
 *
 * */

class Solution {
    private int max;
    public int longestUnivaluePath(TreeNode root) {
        max = 0;
        dfs(root);
        return max;
    }
    private int dfs(TreeNode node){
        if (node == null){
            return 0;
        }

        int left = dfs(node.left), right = dfs(node.right);
        if (node.left != null){
            left = node.left.val == node.val ? left + 1 : 0;
        }
        if (node.right != null){
            right = node.right.val == node.val ? right + 1 : 0;
        }
        max = Math.max(max,left + right);

        return Math.max(left,right);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node21 = new TreeNode(1),
                 node22 = new TreeNode(1),
                 node23 = new TreeNode(5),
                 node11 = new TreeNode(4),
                 node12 = new TreeNode(5),
                 root = new TreeNode(5);
        node11.left = node21;
        node11.right = node22;
        node12.right = node23;
        root.left = node11;
        root.right = node12;
        System.out.println(solution.longestUnivaluePath(root));
    }
}
