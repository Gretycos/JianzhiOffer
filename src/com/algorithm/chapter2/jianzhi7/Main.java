package com.algorithm.chapter2.jianzhi7;

import com.algorithm.TreeNode;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 *
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 *
 *
 * 示例 1:
 *         3
 *        / \
 *       9  20
 *         /  \
 *        15   7
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 示例 2:
 *
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 *
 *
 * */


class Solution {
    private TreeNode build(int[] preorder, int[] inorder, int startPre, int endPre, int startIn, int endIn){
        // 超界
        if (startPre > endPre || startIn > endIn){
            return null;
        }

        // 前序遍历开始的节点是根节点
        TreeNode root = new TreeNode(preorder[startPre]);

        // 在中序序列中找根
        int rootIn = startIn;
        while (rootIn < endIn && inorder[rootIn] != root.val){
            rootIn++;
        }
        // 找不到跟节点返回空
        if (rootIn == endIn && inorder[rootIn] != root.val){
            return null;
        }

        // 左子树长度
        int leftLength = rootIn - startIn;
        root.left = build(preorder,inorder,startPre+1,startPre+leftLength,startIn,rootIn-1);
        root.right = build(preorder,inorder,startPre+leftLength+1,endPre,rootIn+1,endIn);

        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0){
            return null;
        }

        return build(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = solution.buildTree(preorder,inorder);
        TreeNode.preorder(root);
    }
}
