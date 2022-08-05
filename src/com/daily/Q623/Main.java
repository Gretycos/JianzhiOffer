package com.daily.Q623;

import com.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给定一个二叉树的根root和两个整数 val 和depth，在给定的深度depth处添加一个值为 val 的节点行。
 *
 * 注意，根节点root位于深度1。
 *
 * 加法规则如下:
 *
 * 给定整数depth，对于深度为depth - 1 的每个非空树节点 cur ，创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。
 * cur 原来的左子树应该是新的左子树根的左子树。
 * cur 原来的右子树应该是新的右子树根的右子树。
 * 如果 depth == 1 意味着depth - 1根本没有深度，那么创建一个树节点，值 val 作为整个原始树的新根，而原始树就是新根的左子树。
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: root = [4,2,6,3,1,5], val = 1, depth = 2
 * 输出: [4,1,1,2,null,null,6,3,1,5]
 * 示例 2:
 *
 *
 *
 * 输入: root = [4,2,null,3,1], val = 1, depth = 3
 * 输出:  [4,2,null,1,1,3,null,null,1]
 *
 *
 * 提示:
 *
 * 节点数在[1, 10^4]范围内
 * 树的深度在[1, 10^4]范围内
 * -100 <= Node.val <= 100
 * -10^5<= val <= 10^5
 * 1 <= depth <= the depth of tree + 1
 *
 * */

class Solution {
    // BFS
    public TreeNode addOneRow2(TreeNode root, int val, int depth) {
        if (depth == 1){
            return new TreeNode(val,root,null);
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        int currentCount = 1, nextCount = 0;
        while(!queue.isEmpty()){
            TreeNode node = queue.removeFirst();
            if (depth - 1 == 1){
                node.left = new TreeNode(val,node.left,null);
                node.right = new TreeNode(val,null,node.right);
                continue;
            }
            if (node.left != null){
                queue.addLast(node.left);
                nextCount ++;
            }
            if (node.right != null){
                queue.addLast(node.right);
                nextCount ++;
            }
            if (--currentCount == 0){
                depth --;
                currentCount = nextCount;
                nextCount = 0;
            }
        }
        return root;
    }

    // DFS
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (root == null){
            return null;
        }
        if (depth == 1){
            return new TreeNode(val,root,null);
        }
        if (depth == 2){
            root.left = new TreeNode(val,root.left,null);
            root.right = new TreeNode(val,null,root.right);
        }else{
            root.left = addOneRow(root.left,val,depth - 1);
            root.right = addOneRow(root.right,val,depth - 1);
        }
        return root;
    }

}
public class Main {
}
