package com.algorithm.chapter2.jianzhi10_2;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：1
 * 提示：
 *
 * 0 <= n <= 100
 *
 * */

class Solution {
    public int numWays(int n) {
        if (n<2){
            return 1;
        }

        int Nminus2 = 1;
        int Nminus1 = 1;
        int N = 0;

        for (int i = 2; i <= n; i++) {
            N = (int) ((Nminus1 + Nminus2) % (1e9+7));
            Nminus2 = Nminus1;
            Nminus1 = N;
        }

        return N;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numWays(7));
    }
}
