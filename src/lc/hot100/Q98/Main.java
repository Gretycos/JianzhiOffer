package lc.hot100.Q98;


import lc.TreeNode;


/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 *
 * 示例 1：
 *          2
 *         / \
 *        1   3
 *
 * 输入：root = [2,1,3]
 * 输出：true
 *
 * 示例 2：
 *                   5
 *                 /   \
 *                1     4
 *                     / \
 *                    3   6
 *
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 *
 *
 * 提示：
 *
 * 树中节点数目范围在[1, 10^4] 内
 * -2^31 <= Node.val <= 2^31 - 1
 *
 * */
class Solution {
    private TreeNode last;
    public boolean isValidBST(TreeNode root) {
        if (root == null){
            return true;
        }

        if (!isValidBST(root.left)){
            return false;
        }

        if (last != null && last.val >= root.val){ // 出现值相等的节点，不是有效的二叉树
            return false;
        }
        last = root;

        if (!isValidBST(root.right)){
            return false;
        }

        return true;
    }
}
public class Main {
}
