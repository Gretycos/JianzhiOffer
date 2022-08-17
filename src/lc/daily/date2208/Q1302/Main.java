package lc.daily.date2208.Q1302;

import lc.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
 *
 *
 *
 * 示例 1：
 *              1
 *             / \
 *            2   3
 *           / \   \
 *          4   5   6
 *         /         \
 *        7           8
 *
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 * 示例 2：
 *
 * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * 输出：19
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 10^4]之间。
 * 1 <= Node.val <= 100
 *
 * */
class Solution {
    private int maxLevel;
    private int res;
    public int deepestLeavesSum(TreeNode root) {
        maxLevel = 0;
        res = 0;
        dfs(root,0);
        return res;
    }
    private void dfs(TreeNode node, int level){
        if (node == null){
            return;
        }
        if (level > maxLevel){
            res = 0;
            maxLevel = level;
        }
        if (level == maxLevel){
            res += node.val;
        }
        dfs(node.left,level+1);
        dfs(node.right,level+1);
    }
}
public class Main {
    public static void main(String[] args) {
        TreeNode node31 = new TreeNode(7),
                 node32 = new TreeNode(8),
                 node21 = new TreeNode(4),
                 node22 = new TreeNode(5),
                 node23 = new TreeNode(6),
                 node11 = new TreeNode(2),
                 node12 = new TreeNode(3),
                 root = new TreeNode(1);
        node21.left = node31;
        node23.right = node32;
        node11.left = node21;
        node11.right = node22;
        node12.right = node23;
        root.left = node11;
        root.right = node12;
        Solution solution = new Solution();
        System.out.println(solution.deepestLeavesSum(root));
    }
}
