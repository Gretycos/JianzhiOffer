package lc.labuladong.DataStructure.tree.Q226;

import lc.TreeNode;

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
    public TreeNode invertTree2(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode root){
        if (root == null) return;
        // 前序位置
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;

        traverse(root.left);
        traverse(root.right);
    }
}
public class Main {
}
