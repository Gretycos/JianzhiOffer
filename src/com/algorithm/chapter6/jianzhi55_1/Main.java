package com.algorithm.chapter6.jianzhi55_1;

import com.algorithm.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，
 * 最长路径的长度为树的深度。
 *
 * 例如：
 *
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度3 。
 *
 *
 *
 * 提示：
 *
 * 节点总数 <= 10000
 *
 * */

class Solution {

    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return left > right ? left + 1 : right + 1;
    }

    // 循环
    public int maxDepth2(TreeNode root) {
        int current  = 1;
        int next = 0;
        int level = 0;
        if (root == null) return level;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.remove();
            current --;
            if (node.left != null){
                next ++;
                queue.add(node.left);
            }
            if (node.right != null){
                next ++;
                queue.add(node.right);
            }
            if (current == 0){
                level ++;
                current = next;
                next = 0;
            }
        }
        return level;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node31 = new TreeNode(15);
        TreeNode node32 = new TreeNode(7);
        TreeNode node21 = new TreeNode(9);
        TreeNode node22 = new TreeNode(20,node31,node32);
        TreeNode root = new TreeNode(3,node21,node22);
        System.out.println(solution.maxDepth(root));
    }
}
