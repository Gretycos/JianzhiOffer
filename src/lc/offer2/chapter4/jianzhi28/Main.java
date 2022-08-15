package lc.offer2.chapter4.jianzhi28;

import lc.TreeNode;

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
        if (node1 == null || node2 == null){
            return false;
        }
        // 前序遍历 变种
        return node1.val == node2.val && isSymmetricCore(node1.left,node2.right) &&
                isSymmetricCore(node1.right,node2.left);
    }
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetricCore(root.left,root.right);
    }
}
public class Main {
}
