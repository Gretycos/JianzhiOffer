package com.jianzhioffer.chapter2.jianzhi14_2;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
 * 请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 ×3 ×4 = 36
 *
 *
 * 提示：
 *
 * 2 <= n <= 1000
 *
 * */

// 超过int32了不能用动态规划，只能用贪心
class Solution {
    public int cuttingRope(int n) {
        if (n<2) return 0;
        if (n==2) return 1;
        if (n==3) return 2;

        // 尽可能切长度为3的绳子段
        int timesOf3 = n / 3;

        // 当绳子最后剩下的长度为4的时候，转为剪成2+2，因为3*1<2*2
        if (n - timesOf3 * 3 == 1){
            timesOf3 -= 1;
        }

        int timesOf2 = (n - timesOf3 * 3) / 2;

        double res = 1;
        while (timesOf3-- > 0){
            res = res * 3 % (1e9+7);
        }
        while(timesOf2-- > 0){
            res = res * 2 % (1e9+7);
        }

        // (xy) % p = ((x % p)(y % p)) % p
        return (int) res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.cuttingRope(120));
    }
}
