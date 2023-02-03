package lc.daily.date2302.Q1145;

import lc.TreeNode;

class Solution {
    private int xLeft = 0, xRight = 0;
    private boolean foundX = false;
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        dfs(root, x);
        int xElse = n - 1 - xLeft - xRight;
        return xLeft >= (n + 1) / 2 || xRight >= (n + 1) / 2 || xElse >= (n + 1) / 2;
    }

    private void dfs(TreeNode node, int x){
        if (node == null || foundX) return;
        if (node.val == x){
            foundX = true;
            xLeft = dfs(node.left);
            xRight = dfs(node.right);
            return;
        }
        dfs(node.left, x);
        dfs(node.right, x);
    }

    private int dfs(TreeNode node){
        return node == null ? 0 : dfs(node.left) + dfs(node.right) + 1;
    }


}

public class Main {
}
