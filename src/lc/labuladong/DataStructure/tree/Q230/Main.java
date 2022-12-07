package lc.labuladong.DataStructure.tree.Q230;


import lc.TreeNode;

class Solution {
    private int rank;
    private int res;
    public int kthSmallest(TreeNode root, int k) {
        rank = 0;
        res = 0;
        traverse(root, k);
        return res;
    }

    private void traverse(TreeNode root, int k){
        if (root == null) return;
        traverse(root.left, k);
        rank++;
        if (k == rank){
            res = root.val;
            return;
        }
        traverse(root.right, k);
    }
}

public class Main {
}
