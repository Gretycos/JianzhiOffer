package lc.labuladong.DataStructure.tree.Q98;

import lc.TreeNode;

class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,null,null);
    }

    // 限定root 为根的子树节点必须满足 min.val < root.val < max.val
    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max){
        if (root == null) return true;
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;
        return isValidBST(root.left, min, root)
                && isValidBST(root.right, root, max);
    }

}
public class Main {
}
