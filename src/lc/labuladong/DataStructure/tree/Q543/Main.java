package lc.labuladong.DataStructure.tree.Q543;

import lc.TreeNode;

class Solution {
    private int max;
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        dfs(root);
        return max;
    }

    private int dfs(TreeNode node){
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        int diameter = left + right; // 直径计算
        max = Math.max(max,diameter);
        return Math.max(left,right) + 1; // 二叉树高度
    }
}
public class Main {
}
