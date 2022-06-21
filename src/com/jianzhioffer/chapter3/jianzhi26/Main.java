package com.jianzhioffer.chapter3.jianzhi26;

import com.TreeNode;

/**
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *   3
 *   / \
 *  4  5
 *  / \
 * 1  2
 *
 * 给定的树 B：
 *   4
 *  /
 * 1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 *
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 *
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 * 限制：
 *
 * 0 <= 节点个数 <= 10000
 *
 * */

class Solution {
    private boolean isSubStructureCore(TreeNode A, TreeNode B){
        // A空B空 A不空B空
        // 不能先判断A == null，因为只要B是空的就是A的子结构，但是如果A是空B不是空则B不是A的子结构
        if (B == null) {
            return true;
        }

        // A空B不空
        if (A == null){
            return false;
        }

        // A不空B不空
        return A.val == B.val && isSubStructureCore(A.left,B.left) && isSubStructureCore(A.right,B.right);
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 空树不是任意一个树的子结构
        if (A == null || B == null) {
            return false;
        }
        // 判断A、B有没有相同的结构 || 判断B是否是A的左子树的子结构 || 判断B是否是A的右子树的子结构
        return isSubStructureCore(A,B) || isSubStructure(A.left,B) || isSubStructure(A.right,B);
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode A = new TreeNode(10);
        TreeNode A1 = new TreeNode(12);
        TreeNode A2 = new TreeNode(6);
        A.left = A1;
        A.right = A2;
        TreeNode A3  = new TreeNode(8);
        TreeNode A4 = new TreeNode(3);
        TreeNode A5 = new TreeNode(11);
        A1.left = A3;
        A1.right = A4;
        A2.left = A5;

        TreeNode B = new TreeNode(10);
        TreeNode B1 = new TreeNode(12);
        TreeNode B2 = new TreeNode(6);
        B.left = B1;
        B.right = B2;
        B1.left = new TreeNode(8);

        System.out.println(solution.isSubStructure(A,B));
    }
}
