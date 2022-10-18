package lc.hot100.Q76toQ100.Q437;

import lc.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二叉树的根节点 root，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 *
 *
 * 示例 1：
 *                       10
 *                    /     \
 *                   5      -3
 *                 /  \       \
 *                3    2       11
 *               / \    \
 *              3   -2   1
 *
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 *
 *
 * 示例 2：
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,1000]
 * -10^9<= Node.val <= 10^9
 * -1000<= targetSum<= 1000
 *
 * */
class Solution {
    // dfs 暴力
    // Time: O(n^2)
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null){
            return 0;
        }
        return dfs(root, targetSum) + pathSum(root.left,targetSum) + pathSum(root.right,targetSum);
    }

    private int dfs(TreeNode node, long targetSum){
        if (node == null){
            return 0;
        }
        int count = 0;
        long val = node.val;
        if (val == targetSum){
            count ++;
        }
        return count + dfs(node.left, targetSum - val) + dfs(node.right, targetSum - val);
    }

    // 前缀和
    // Time: O(n)
    private Map<Long, Integer> map;
    public int pathSum2(TreeNode root, int targetSum) {
        map = new HashMap<>();
        // 当targetSum == curSum时，次数是1
        map.put(0L,1);
        return dfs(root, 0, targetSum);
    }

    private int dfs(TreeNode node, long curSum, int targetSum){
        if (node == null){
            return 0;
        }

        // 当前和
        curSum += node.val;

        // 查询是否存在一个数preSum，使得curSum - preSum = targetSum
        // 如果有，则加上它的次数
        int count = map.getOrDefault(curSum - targetSum, 0);

        // 存储/更新当前前缀和
        map.put(curSum,map.getOrDefault(curSum,0) + 1);

        // 左右子树的情况
        count += dfs(node.left, curSum, targetSum);
        count += dfs(node.right, curSum, targetSum);

        // 回溯
        map.put(curSum,map.getOrDefault(curSum,0) - 1);
        return count;
    }
}
public class Main {
}
