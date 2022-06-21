package com;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int val) { this.val = val; }

    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void preorder(TreeNode root){
        if (root==null){
            return;
        }
        System.out.print(root.val + " ");
        if (root.left!=null){
            preorder(root.left);
        }
        if (root.right!=null){
            preorder(root.right);
        }
    }
}
