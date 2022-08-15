package lc.daily.date2206.Q515;
import lc.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


/**
 * 给定一棵二叉树的根节点root ，请找出该二叉树中每一层的最大值。
 *
 *
 *
 * 示例1：
 *
 *              1
 *             / \
 *            3   2
 *           / \   \
 *          5   3   9
 *
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * 示例2：
 *
 *      1
 *     / \
 *    2   3
 *
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 *
 *
 * 提示：
 *
 * 二叉树的节点个数的范围是 [0,104]
 * -2^31 <= Node.val <= 2^31- 1
 *
 * */
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Queue<TreeNode> Q = new ArrayDeque<>();
        Q.add(root);

        int cur = 1, next = 0;
        int max = Integer.MIN_VALUE;

        while(!Q.isEmpty()){
            TreeNode node = Q.remove();
            max = Math.max(node.val, max);
            if (node.left != null){
                Q.add(node.left);
                next ++;
            }
            if (node.right != null){
                Q.add(node.right);
                next ++;
            }
            if (--cur == 0){
                res.add(max);
                max = Integer.MIN_VALUE;
                cur = next;
                next = 0;
            }
        }

        return res;
    }
}
public class Main {
}
