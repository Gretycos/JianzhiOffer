package com.jianzhioffer.chapter4.jianzhi27;

import com.TreeNode;

/**
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 * 例如输入：
 *
 *     4
 *   /  \
 *   2   7
 * / \  / \
 * 1  3 6  9
 * 镜像输出：
 *
 *     4
 *    /  \
 *   7   2
 *  / \  / \
 * 9  6 3   1
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 1000
 *
 * */

class Solution {
    private void mirrorTreeCore(TreeNode node){
        if (node == null){
            return;
        }

        // 后序遍历 变种
        mirrorTreeCore(node.right);
        mirrorTreeCore(node.left);

        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        mirrorTreeCore(root);
        return root;
    }
}
public class Main {
    public static void main(String[] args) {

    }
}
