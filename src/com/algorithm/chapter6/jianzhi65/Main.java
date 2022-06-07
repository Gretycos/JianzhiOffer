package com.algorithm.chapter6.jianzhi65;

/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 *
 * 示例:
 *
 * 输入: a = 1, b = 1
 * 输出: 2
 *
 * 提示：
 *
 * a,b均可能是负数或 0
 * 结果不会溢出 32 位整数
 *
 * */

class Solution {
    public int add(int a, int b) {
        int sum = a, carry = b, temp;
        do {
            temp = sum;
            // 1. 不进位加，相当于位异或
            sum = temp ^ carry;
            // 2. 计算进位，相当于位与再左移
            carry = (temp & carry) << 1;
            // 3. 只要进位不为0，就继续计算

        }while (carry != 0);
        return sum;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.add(4,17));
    }
}
