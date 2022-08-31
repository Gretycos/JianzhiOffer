package lc.hot100.Q124;


import lc.TreeNode;

/**
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 *
 *
 * 示例 1：
 *      1
 *     / \
 *    2   3
 *
 *
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 *
 *
 * 示例 2：
 *       -10
 *       /  \
 *      9   20
 *         /  \
 *        15   7
 *
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 *
 * 提示：
 *
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 *
 *
 * */

class Solution {
    private int maxSum;
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        getGain(root); // 求每个节点的贡献值，中途更新最大和
        return maxSum;
    }
    private int getGain(TreeNode root){
        if (root == null){
            return 0;
        }

        int leftGain = Math.max(getGain(root.left), 0),
            rightGain = Math.max(getGain(root.right), 0);
        maxSum = Math.max(maxSum,root.val + leftGain + rightGain);
        // 节点的贡献值 = 节点的值 + 左右子节点的最大的贡献值
        return root.val + Math.max(leftGain,rightGain);
    }


}
public class Main {
}
