package com.algorithm.chapter4.jianzhi34;

import com.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *              5
 *             / \
 *            4   8
 *           /   / \
 *          11  13  4
 *         / \     /
 *        7   2   5
 *
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 *       1
 *      / \
 *     2   3
 *
 *
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 *
 *
 * 提示：
 *
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 * */

class Solution {
    private List<List<Integer>> res;

    private int target;

    private List<Integer> path;

    private void pathSumCore(TreeNode node, int sum){
        if (node == null){
            return;
        }

        // 前序遍历
        // 根
        if (node.left == null && node.right == null){
            if (sum + node.val == target){
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(node.val);
                res.add(newPath);
            }
            return;
        }
        path.add(node.val);
        // 左
        pathSumCore(node.left,sum + node.val);
        // 右
        pathSumCore(node.right,sum + node.val);
        // 回溯
        path.remove(path.size()-1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        res = new ArrayList<>();
        if (root == null) return res;
        this.target = target;
        path = new ArrayList<>();
        pathSumCore(root,0);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node31 = new TreeNode(7);
        TreeNode node32 = new TreeNode(2);
        TreeNode node33 = new TreeNode(5);
        TreeNode node34 = new TreeNode(1);
        TreeNode node21 = new TreeNode(11,node31,node32);
        TreeNode node22 = new TreeNode(13);
        TreeNode node23 = new TreeNode(4,node33,node34);
        TreeNode node11 = new TreeNode(4,node21,null);
        TreeNode node12 = new TreeNode(8,node22,node23);
        TreeNode root = new TreeNode(5,node11,node12);
        System.out.println(solution.pathSum(root,22));
    }
}
