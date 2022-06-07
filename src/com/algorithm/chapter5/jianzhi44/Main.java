package com.algorithm.chapter5.jianzhi44;

/**
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。
 * 在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 请写一个函数，求任意第n位对应的数字。
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 *
 * 输入：n = 11
 * 输出：0
 *
 *
 * 限制：
 *
 * 0 <= n < 2^31
 *
 * */



class Solution {

    private int digitAtIndex(int idx, int digits) {
        // 开始的数字
        int beginNum = digits == 1? 0 : (int) Math.pow(10,digits-1);
        // 所在的数字
        int num = beginNum + idx / digits;
        int p = idx % digits;  // 从左数第几位，下标从0开始
        // 从一个数找第几位
        return num / (int) Math.pow(10,digits - 1 - p) % 10;
    }

    public int findNthDigit(int n) {
        int idx = n; // 索引
        int digits = 1; // 几位数
        while (true){
            // 有几个数字
            int numbers = digits == 1 ? 10 : 9 * (int) Math.pow(10,digits-1);
            if (idx < (long) numbers * digits){ // numbers * digits 会超int范围
                return digitAtIndex(idx,digits);
            }
            idx -= digits * numbers;
            digits ++ ;
        }
    }


}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(
                solution.findNthDigit(1000000000)
        );
    }
}
