package lc.hot100.Q26to50.Q94;

import lc.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序遍历 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 *
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * */

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()){
            while (root != null){
                // 根节点压栈
                stack.addLast(root);
                // 访问左节点
                root = root.left;
            }
            root = stack.removeLast();
            // 访问根节点
            res.add(root.val);
            // 访问右节点
            root = root.right;
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        TreeNode node21 = new TreeNode(3),
                 node11 = new TreeNode(2),
                 root = new TreeNode(1);
        node11.left = node21;
        root.right = node11;
        Solution solution = new Solution();
        System.out.println(solution.inorderTraversal(root));
    }
}
