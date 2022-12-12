package lc.labuladong.DataStructure.tree.Q222;

import lc.TreeNode;

class Solution {
    public int countNodes(TreeNode root) {
        TreeNode l = root, r = root;
        int hl = 0, hr = 0;
        while (l != null){
            l = l.left;
            hl ++;
        }
        while (r != null){
            r = r.right;
            hr ++;
        }
        // 左右子树高度相同，则为满二叉树
        if (hl == hr){
            return (int) Math.pow(2, hl) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}

public class Main {
}
