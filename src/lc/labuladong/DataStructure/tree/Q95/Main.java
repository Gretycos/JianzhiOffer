package lc.labuladong.DataStructure.tree.Q95;


import lc.TreeNode;

import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<TreeNode> generateTrees(int n) {
        return build(1,n);
    }

    private List<TreeNode> build(int lo, int hi){
        List<TreeNode> res = new LinkedList<>();
        if (lo > hi){
            res.add(null);
            return res;
        }

        for (int mid = lo; mid <= hi ; mid++) {
            List<TreeNode> left = build(lo,mid-1);
            List<TreeNode> right = build(mid + 1, hi);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(mid);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }

        return res;
    }
}


public class Main {
}
