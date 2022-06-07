package com.algorithm.chapter4.jianzhi32_1;

import com.algorithm.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;


/**
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
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
 *
 * 返回：
 *
 * [3,9,20,15,7]
 *
 *
 * 提示：
 *
 * 节点总数 <= 1000
 *
 * */

class Solution {
    public int[] levelOrder(TreeNode root) {
        if (root == null){
            return new int[]{};
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        ArrayList<Integer> res = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.remove();
            res.add(node.val);
            if (node.left != null){
                queue.add(node.left);
            }
            if (node.right != null){
                queue.add(node.right);
            }
        }

        int[] result = new int[res.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
