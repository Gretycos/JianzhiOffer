package lc.hot100.Q76toQ100.Q337;

import lc.TreeNode;

/**
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。
 *
 * 除了root之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 *
 * 给定二叉树的root。返回在不触动警报的情况下，小偷能够盗取的最高金额。
 *
 *
 *
 * 示例 1:
 *              3
 *             / \
 *            2   3
 *             \   \
 *              3   1
 *
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释:小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 *
 *
 * 示例 2:
 *               3
 *             /  \
 *            4    5
 *           / \    \
 *          1  3     1
 *
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释:小偷一晚能够盗取的最高金额 4 + 5 = 9
 *
 * 提示：
 *
 * 树的节点数在 [1, 10^4] 范围内
 * 0 <= Node.val <= 10^4
 * */

class Solution {
    // dp
    // 定义：
    //  choose(i)：选择节点i的情况下，i节点在子树上被选择的节点的最大权值之和
    //  notChoose(i)：不选择节点i的情况下，i节点在子树上被选择的节点的最大权值之和
    // 转移：
    //  choose(i) = i.val + notChoose(i.left) + notChoose(i.right)
    //  notChoose(i) = max{choose(i.left), notChoose(i.left)}
    //                  + max{choose(i.right), notChoose(i.right)}
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0],res[1]);
    }

    private int[] dfs(TreeNode node){
        if (node == null){
            return new int[]{0,0};
        }
        int[] resLeft = dfs(node.left);
        int[] resRight = dfs(node.right);
        int choose = node.val + resLeft[1] + resRight[1];
        int notChoose = Math.max(resLeft[0],resLeft[1])
                + Math.max(resRight[0],resRight[1]);
        return new int[]{choose,notChoose};
    }
}


public class Main {
}
