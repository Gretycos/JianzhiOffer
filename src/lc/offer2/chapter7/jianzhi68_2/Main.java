package lc.offer2.chapter7.jianzhi68_2;

import lc.TreeNode;

import java.util.Stack;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 *               3
 *            /     \
 *           5       1
 *          / \     / \
 *         6   2   0   8
 *            / \
 *           7   4
 *
 *
 *
 * 示例 1:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例2:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 *
 * 说明:
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 */
class Solution {
    private boolean findNode(TreeNode node, TreeNode target, Stack<TreeNode> s){
        if (node == null)
            return false;

        s.push(node);
        if (node == target){
            return true;
        }
        if (findNode(node.left, target, s)){
            return true;
        }
        if (findNode(node.right,target,s)){
            return true;
        }
        s.pop();
        return false;
    }

    // 遍历2次
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        if(findNode(root,p,s1) && findNode(root,q,s2)){
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
        }
        return null;
    }

    // 遍历1次
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if (root == null){
            return null;
        }
        if (root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        // 一个在左节点一个在右节点
        if (left != null && right != null){
            return root;
        }
        // 都在左节点
        if (left != null){
            return left;
        }
        // 都在右节点
        if (right != null){
            return right;
        }
        return null;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node41 = new TreeNode(7);
        TreeNode node42 = new TreeNode(4);
        TreeNode node31 = new TreeNode(6);
        TreeNode node32 = new TreeNode(2,node41,node42);
        TreeNode node33 = new TreeNode(0);
        TreeNode node34 = new TreeNode(8);
        TreeNode node21 = new TreeNode(5,node31,node32);
        TreeNode node22 = new TreeNode(1,node33,node34);
        TreeNode root = new TreeNode(3,node21,node22);
        System.out.println(solution.lowestCommonAncestor(root,node41,node42).val);

    }
}