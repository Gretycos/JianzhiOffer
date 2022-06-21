package com.jianzhioffer.chapter3.jianzhi16;

/**
 * 实现pow(x,n)，即计算 x 的 n 次幂函数（即，x^n）。
 * 不得使用库函数，同时不需要考虑大数问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 *
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 *
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *
 *
 * 提示：
 *
 * -100.0 <x< 100.0
 * -2^31<= n <=2^31-1
 * -10^4<= xn<= 10^4
 *
 * */

class Solution {
    // 时间O(logn)
    // 空间O(logn)
    private double pow(double x, long n){
        if (n == 0) return 1;
        if (n == 1) return x;

        double result = pow(x, n >> 1);

        result *= result;

        // 奇数多乘以一次底
        return (n & 1) == 1 ? result * x : result;

    }
    public double myPow2(double x, int n) {
        double result;
        if (n < 0){
            result = pow(x, -(long) n); // int的最小值取反会超界
            result = 1 / result;
        } else {
            result = pow(x, n);
        }
        return result;
    }

    // 基于循环
    // 省空间，但是推导思路不容易
    /**
     * x^77 为例子
     * x -> x^2 -> x^4 -> +x^9 -> +x^19 -> x^38 -> +x^77
     * 需要额外乘以 x 的步骤打上 + 标记
     * x^38 -> +x^77 额外的 x 在 x^77 贡献了x
     * x^9 -> +x^19 额外的 x 在之后被平方了2次，所以它在 x^77 中贡献了x^2^2 = x^4
     * x^4 -> +x^9 额外的 x 在之后被平方了3次，所以它在 x^77 中贡献了x^2^3 = x^8
     * 最初的 x 在之后被平方了6次，所以它在 x^77 中贡献了x^2^6 = x^64
     * x * x^4 * x^8 * x^64 = x^77
     * 指数1 4 8 64 代表了77的二进制表示0100 1101的每个1
     * 所以n的第k位为1，则需要把贡献计入答案
     * */
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        long N = n;
        if (n < 0){
            x = 1 / x;
            N = -N;
        }
        double res = 1.0;
        while (N > 0){
            // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
            if ((N & 1) == 1){
                res *= x;
            }
            // 将贡献不断地平方
            x *= x;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N >>= 1;
        }
        return res;
    }

    // 时间O(n)，超时
    public double myPow3(double x, int n) {
        if(n == 0) return 1;
        int N = Math.abs(n);
        double res = 1;
        while(N -- > 0){
            res *= x;
        }
        return n < 0 ? 1.0 / res : res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.myPow(2,-2));
    }
}
