package lc.labuladong.DataStructure.tree.Q1644;

import lc.TreeNode;

class Solution{

    private boolean foundP = false, foundQ = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = find(root, p.val, q.val);
        // 有一个节点不存在
        if (!foundP || !foundQ){
            return null;
        }
        return res;
    }

    private TreeNode find(TreeNode root, int val1, int val2){
        if (root == null) return null;
        TreeNode left = find(root.left, val1, val2);
        TreeNode right = find(root.right, val1, val2);
        if (left != null && right != null){
            return root;
        }

        if (root.val == val1 || root.val == val2){
            if (root.val == val1) foundP = true;
            if (root.val == val2) foundQ = true;
            return root;
        }

        return left != null ? left : right;
    }
}


public class Main {
}
