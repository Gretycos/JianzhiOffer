package lc.labuladong.DataStructure.tree.Q1038;

import lc.TreeNode;

class Solution {
    private int sum;
    public TreeNode bstToGst(TreeNode root) {
        sum = 0;
        traverse(root);
        return root;
    }

    private void traverse(TreeNode root){
        if (root == null) return;
        traverse(root.right);
        sum += root.val;
        root.val = sum;
        traverse(root.left);
    }
}

public class Main {
}
