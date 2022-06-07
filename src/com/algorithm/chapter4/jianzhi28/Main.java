package com.algorithm.chapter4.jianzhi28;

import com.algorithm.TreeNode;

/**
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树[1,2,2,3,4,4,3] 是对称的。
 *
 *   1
 *  / \
 *  2  2
 * / \ / \
 * 3 4 4 3
 * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *   1
 *  / \
 *  2  2
 *  \  \
 *  3  3
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 * */

class Solution {
    private boolean isSymmetricCore(TreeNode node1, TreeNode node2){
        // 空节点
        if (node1 == null && node2 == null){
            return true;
        }
        // 一个空一个不空
        if (node1 == null || node2 == null){
            return false;
        }
        // 两个都不空
        if (node1.val != node2.val){
            return false;
        }

        return isSymmetricCore(node1.left,node2.right) &&
                isSymmetricCore(node1.right,node2.left);
    }
    public boolean isSymmetric(TreeNode root) {
        return isSymmetricCore(root,root);
    }
}
public class Main {
}
