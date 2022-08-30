package lc.daily.date2208.Q998;

import lc.TreeNode;

/**
 * 最大树 定义：一棵树，并满足：其中每个节点的值都大于其子树中的任何其他值。
 *
 * 给你最大树的根节点 root 和一个整数 val 。
 *
 * 就像 之前的问题 那样，给定的树是利用 Construct(a)例程从列表a（root = Construct(a)）递归地构建的：
 *
 * 如果 a 为空，返回null 。
 * 否则，令a[i] 作为 a 的最大元素。创建一个值为a[i]的根节点 root 。
 * root的左子树将被构建为Construct([a[0], a[1], ..., a[i - 1]]) 。
 * root的右子树将被构建为Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]]) 。
 * 返回root 。
 * 请注意，题目没有直接给出 a ，只是给出一个根节点root = Construct(a) 。
 *
 * 假设 b 是 a 的副本，并在末尾附加值 val。题目数据保证 b 中的值互不相同。
 *
 * 返回Construct(b) 。
 *
 *
 *
 * 示例 1：
 *          4                   5
 *         / \                 /
 *        1   3               4
 *           /               / \
 *          2               1   3
 *                             /
 *                            2
 *
 * 输入：root = [4,1,3,null,null,2], val = 5
 * 输出：[5,4,null,1,3,null,null,2]
 * 解释：a = [1,4,2,3], b = [1,4,2,3,5]
 *
 *
 * 示例 2：
 *           5                  5
 *          / \                / \
 *         2   4              2   4
 *          \                  \   \
 *           1                  1   3
 *
 * 输入：root = [5,2,4,null,1], val = 3
 * 输出：[5,2,4,null,1,null,3]
 * 解释：a = [2,1,5,4], b = [2,1,5,4,3]
 *
 *
 * 示例 3：
 *           5                    5
 *          / \                /    \
 *         2   3              2      4
 *          \                  \    /
 *           1                  1  3
 * 输入：root = [5,2,3,null,1], val = 4
 * 输出：[5,2,4,null,1,3]
 * 解释：a = [2,1,5,3], b = [2,1,5,3,4]
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 100] 内
 * 1 <= Node.val <= 100
 * 树中的所有值 互不相同
 * 1 <= val <= 100
 *
 * */

class Solution {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode pp = null, p = root;
        while (p != null && p.val > val){
            pp = p;
            p = p.right;
        }
        if (pp == null){
            pp = new TreeNode(val);
            pp.left = root;
            return pp;
        }
        pp.right = new TreeNode(val);
        pp.right.left = p;
        return root;
    }
}
public class Main {
}
