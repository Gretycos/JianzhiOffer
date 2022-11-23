package lc.labuladong.DataStructure.tree.Q144;

import lc.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<Integer> res;
    public List<Integer> preorderTraversal(TreeNode root) {
        res = new ArrayList<>();
        traverse(root);
        return res;
    }

    private void traverse(TreeNode node){
        if (node == null) return;
        res.add(node.val);
        traverse(node.left);
        traverse(node.right);
    }
}

public class Main {
}
