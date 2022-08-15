package lc.offer2.chapter4.jianzhi32_3;

import lc.TreeNode;

import java.util.*;

/**
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 *
 *
 *
 * 例如:
 * 给定二叉树:[3,9,20,null,null,15,7],
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
 *   [20,9],
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
        boolean isOdd = true; // 本层奇偶
        List<Integer> layer = new LinkedList<>(); // 本层数据

        while(!queue.isEmpty()){
            TreeNode node = queue.remove();
            if (!isOdd){ // 偶数
                layer.add(0,node.val);
            } else {
                layer.add(node.val);
            }
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
                isOdd = !isOdd;
            }
        }

        return res;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
