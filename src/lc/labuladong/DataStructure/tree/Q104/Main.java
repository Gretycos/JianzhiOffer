package lc.labuladong.DataStructure.tree.Q104;

import lc.TreeNode;

class Solution {
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }
}
public class Main {
}
