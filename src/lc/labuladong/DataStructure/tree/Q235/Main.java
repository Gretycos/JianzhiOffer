package lc.labuladong.DataStructure.tree.Q235;

import lc.TreeNode;

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 保证val1 < val2
        if (p.val > q.val){
            return lowestCommonAncestor(root, q, p);
        }
        return find(root, p.val, q.val);
    }

    private TreeNode find(TreeNode root, int val1, int val2){
        if (root == null) return null;

        if (root.val > val2){
            return find(root.left, val1, val2);
        }

        if (root.val < val1){
            return find(root.right, val1, val2);
        }
        // val1 <= root.val <= val2
        return root;
    }
}


public class Main {
}
