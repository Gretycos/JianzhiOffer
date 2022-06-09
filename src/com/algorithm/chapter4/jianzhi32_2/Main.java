package com.algorithm.chapter4.jianzhi32_2;

import com.algorithm.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 *
 *
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 *
 * 提示：
 *
 * 节点总数 <= 1000
 *
 * */


class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null){
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();

        queue.add(root);
        int thisLevel = 1; // 本层计数器
        int nextLevel = 0; // 下层计数器
        List<Integer> layer = new ArrayList<>(); // 本层数据

        while(!queue.isEmpty()){
            TreeNode node = queue.remove();
            layer.add(node.val);
            if (node.left != null){
                queue.add(node.left);
                nextLevel ++;
            }
            if (node.right != null){
                queue.add(node.right);
                nextLevel ++;
            }
            if (--thisLevel == 0){
                res.add(layer);
                layer = new ArrayList<>(); // 重置
                thisLevel = nextLevel;
                nextLevel = 0;
            }
        }

        return res;
    }
}


public class Main {
    public static void main(String[] args) {

    }
}
