package lc.daily.date2302.Q2331;

import lc.TreeNode;

class Solution {
    public boolean evaluateTree(TreeNode root) {
        if (root.left == null && root.right == null) return root.val == 1;
        boolean left = evaluateTree(root.left), right = evaluateTree(root.right);
        return root.val == 2 ? left || right : left && right;
    }
}

public class Main {
}
