package lc.labuladong.DataStructure.tree.Q114;

import lc.TreeNode;

class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.left);
        flatten(root.right);
        // 后序位置
        // 记录左右指针
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 左子树作为右子树
        root.left = null;
        root.right = left;
        // 原来右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null){
            p = p.right;
        }
        p.right = right;
    }
}
public class Main {
}
