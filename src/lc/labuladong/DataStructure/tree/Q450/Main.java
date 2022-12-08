package lc.labuladong.DataStructure.tree.Q450;

import lc.TreeNode;

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key == root.val){
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // 都不为空
            // 寻找右子树的最小节点
            TreeNode minNode = getMin(root.right);
            // 从右子树中删除minNode，并返回右子树的根节点
            root.right = deleteNode(root.right, minNode.val);
            // 用minNode替代root
            minNode.left = root.left;
            minNode.right = root.right;
            root = minNode;
        }else if (key < root.val){
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }

        return root;
    }

    private TreeNode getMin(TreeNode node){
        while (node.left != null){
            node = node.left;
        }
        return node;
    }
}

public class Main {
}
