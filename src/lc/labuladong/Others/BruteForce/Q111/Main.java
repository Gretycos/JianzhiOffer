package lc.labuladong.Others.BruteForce.Q111;

import lc.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.remove();
                /* 判断是否到达终点 */
                if (cur.left == null && cur.right == null){
                    return depth;
                }
                /* 将 cur 的相邻节点加入队列 */
                if (cur.left != null){
                    q.add(cur.left);
                }
                if (cur.right != null){
                    q.add(cur.right);
                }
            }
            /* 这里增加步数 */
            depth ++;
        }

        return depth;
    }
}

public class Main {
}
