package lc.hot100.Q96;

/**
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：5
 *
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= n <= 19
 *
 * */

class Solution {
    public int numTrees(int n) {
        // 给定序列1~n，我们选择数字 i 作为根，则根为 i 的所有二叉搜索树的集合是左子树集合和右子树集合的笛卡尔积，
        // 对于笛卡尔积中的每个元素，加上根节点之后形成完整的二叉搜索树，

        // G(n)：长为n的序列能构成的不同的bst的个数
        // F(i,n)：以i为根的，长为n的序列的不同的bst的个数
        // G(n) = sigma(i=1,n)(F(i,n))
        // F(i,n) = G(i-1) *G (n-i)
        // G(n) = sigma(i=1,n)(G(i-1) * G(n-i))
        int[] G = new int[n+1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j-1] * G[i-j];
            }
        }
        return G[n];
    }
}

public class Main {
}
