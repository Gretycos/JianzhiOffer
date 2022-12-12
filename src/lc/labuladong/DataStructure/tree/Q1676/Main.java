package lc.labuladong.DataStructure.tree.Q1676;

import lc.TreeNode;

import java.util.HashSet;

class Solution{
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes){
        HashSet<Integer> values = new HashSet<>();
        for (TreeNode node : nodes) {
            values.add(node.val);
        }
        return find(root, values);
    }

    private TreeNode find(TreeNode root, HashSet<Integer> values){
        if (root == null) return null;
        if (values.contains(root.val)){
            return root;
        }

        TreeNode left = find(root.left, values);
        TreeNode right = find(root.right, values);
        if (left != null && right != null){
            return root;
        }

        return left != null ? left : right;
    }
}
public class Main {
}
