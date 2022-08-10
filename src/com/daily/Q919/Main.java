package com.daily.Q919;

import com.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 完全二叉树 是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
 *
 * 设计一种算法，将一个新节点插入到一个完整的二叉树中，并在插入后保持其完整。
 *
 * 实现 CBTInserter 类:
 *
 * CBTInserter(TreeNode root)使用头节点为root的给定树初始化该数据结构；
 * CBTInserter.insert(int v) 向树中插入一个值为Node.val == val的新节点TreeNode。
 * 使树保持完全二叉树的状态，并返回插入节点TreeNode的父节点的值；
 * CBTInserter.get_root() 将返回树的头节点。
 *
 *
 * 示例 1：
 *         1              1                 1
 *        / \    ->      / \    ->         / \
 *       2              2   3             2   3
 *                                       /
 *                                      4
 * 输入
 * ["CBTInserter", "insert", "insert", "get_root"]
 * [[[1, 2]], [3], [4], []]
 * 输出
 * [null, 1, 2, [1, 2, 3, 4]]
 *
 * 解释
 * CBTInserter cBTInserter = new CBTInserter([1, 2]);
 * cBTInserter.insert(3);  // 返回 1
 * cBTInserter.insert(4);  // 返回 2
 * cBTInserter.get_root(); // 返回 [1, 2, 3, 4]
 *
 *
 * 提示：
 *
 * 树中节点数量范围为[1, 1000]
 * 0 <= Node.val <= 5000
 * root是完全二叉树
 * 0 <= val <= 5000
 * 每个测试用例最多调用insert和get_root操作10^4次
 *
 * */

class CBTInserter {
    private TreeNode root;
    // 插入时会用到的父节点队列
    private LinkedList<TreeNode> parents;

    public CBTInserter(TreeNode root) {
        this.root = root;
        parents = new LinkedList<>();
        if (root == null) return;
        // 寻找只有1 或 0 个子节点的节点，插入的时候会用到
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.removeFirst();
            if (node.left != null){
                queue.addLast(node.left);
            }
            if (node.right != null){
                queue.addLast(node.right);
            }
            // 只有1个子节点或者没有子节点
            // 加入parents候选
            if (node.left == null || node.right == null){
                parents.addLast(node);
            }
        }
    }

    public int insert(int val) {
        TreeNode child = new TreeNode(val);
        TreeNode parent = parents.getFirst();
        // left为空说明没有子节点
        if (parent.left == null){
            parent.left = child;
        }else{ // left不为空，说明有1个子节点
            parent.right = child;
            // 填充满了要从候选中移除
            parents.removeFirst();
        }
        // 新加入的节点没有子节点，需要加入候选
        parents.addLast(child);
        return parent.val;
    }

    public TreeNode get_root() {
        return root;
    }

}


public class Main {
}
