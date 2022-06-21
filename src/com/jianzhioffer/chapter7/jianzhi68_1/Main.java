package com.jianzhioffer.chapter7.jianzhi68_1;

import com.TreeNode;

import java.util.Stack;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 *            6
 *         /    \
 *        2      8
 *       / \    / \
 *      0   4  7   9
 *         / \
 *        3   5
 *
 *
 * 示例 1:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *
 *
 * 说明:
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 *
 * */

class Solution {

    private void findNode(TreeNode node, TreeNode target,Stack<TreeNode> s){
        if (node == null)
            return;

        s.push(node);
        if (node == target){
            return;
        }
        if (target.val > node.val){
            findNode(node.right,target,s);
        } else {
            findNode(node.left,target,s);
        }
    }
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        findNode(root,p, s1);
        findNode(root,q, s2);
        while (s1.size() > s2.size()){
            s1.pop();
        }
        while(s2.size() > s1.size()){
            s2.pop();
        }
        while(!s1.isEmpty() && !s2.isEmpty()){
            TreeNode node = s1.pop();
            if (node == s2.pop()){
                return node;
            }
        }
        return null;
    }
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        if (root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right,p,q);
        }
        return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        TreeNode ancestor = root;
        while(true){
            if (ancestor.val > p.val && ancestor.val > q.val){
                ancestor = ancestor.left;
            } else if (ancestor.val < p.val && ancestor.val < q.val){
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }
}
public class Main {
}
