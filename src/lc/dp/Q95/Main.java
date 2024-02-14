package lc.dp.Q95;

import lc.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Tsong
 * @Date 2023/9/16 14:24
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return dp(1,n);
    }

    private List<TreeNode> dp(int lo, int hi) {
        List<TreeNode> res = new ArrayList<>();
        if (lo > hi) {
           res.add(null);
           return res;
        }

        for (int i = lo; i <= hi; i++) {
            List<TreeNode> leftTree = dp(lo, i-1);
            List<TreeNode> rightTree = dp(i+1, hi);
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
public class Main {
}
