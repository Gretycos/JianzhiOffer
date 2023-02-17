package lc.labuladong.DynamicProgress.game.Q337;

import lc.TreeNode;

import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        // base case
        if (root == null){
            return 0;
        }
        // 备忘录
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        // 抢，然后去下下家
        int rob = root.val
                + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
        // 不抢，然后去下家
        int not_rob = rob(root.left) + rob(root.right);
        // 取最大值
        int res = Math.max(rob, not_rob);
        memo.put(root, res);
        return res;
    }

    public int rob2(TreeNode root) {
        int[] res = dp(root);
        return Math.max(res[0],res[1]);
    }

    // 定义：
    //  res[0]表示不抢root，得到的最大钱数
    //  res[1]表示抢root，得到的最大钱数
    private int[] dp(TreeNode root){
        if (root == null) {
            return new int[]{0,0};
        }
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        // 抢root
        int rob = root.val + left[0] + right[0];
        // 不抢root，下家可抢可不抢
        int not_rob = Math.max(left[0], left[1])
                + Math.max(right[0], right[1]);
        return new int[]{not_rob, rob};
    }
}

public class Main {
}
