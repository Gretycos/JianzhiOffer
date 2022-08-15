package lc.offer2.chapter2.jianzhi10_1;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 * F(0) = 0, F(1)= 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *
 *
 * 提示：
 *
 * 0 <= n <= 100
 *
 * */
class Solution {
    public int fib(int n) {
        if (n < 2){
            return n;
        }

        int FNminus2=0;
        int FNminus1=1;
        int FN=0;

        for (int i=2; i<=n; i++){
            FN = (int) ((FNminus1 + FNminus2)%(1e9+7));
            FNminus2 = FNminus1;
            FNminus1 = FN;
        }

        return FN;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.fib(81));
    }
}
