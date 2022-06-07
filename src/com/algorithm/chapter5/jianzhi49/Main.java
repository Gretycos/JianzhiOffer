package com.algorithm.chapter5.jianzhi49;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 *  
 *
 * 示例:
 *
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:
 *
 * 1是丑数。
 * n不超过1690。
 *
 * */

class Solution {


    // 3指针
    public int nthUglyNumber2(int n) {
        int[] uglyNums = new int[n];
        uglyNums[0] = 1;
        int p = 1;
        int p2 = 0, p3 = 0, p5 = 0;

        while (p < n){
            // 最小值
            int min = Math.min(Math.min(uglyNums[p2] * 2, uglyNums[p3] * 3), uglyNums[p5] * 5);
            uglyNums[p++] = min;
            // 最小值从哪里来的，指针就自增
            if (uglyNums[p2] * 2 == min) p2++;
            if (uglyNums[p3] * 3 == min) p3++;
            if (uglyNums[p5] * 5 == min) p5++;
        }

        return uglyNums[n-1];
    }

    // 3队列
    public int nthUglyNumber(int n) {
        final int[] bases = {2,3,5};
        int result = 1;

        Queue<Integer>[] queues = new Queue[3];
        queues[0] =  new ArrayDeque<>();
        queues[1] = new ArrayDeque<>();
        queues[2] = new ArrayDeque<>();

        int p = 1;
        queues[0].add(2);
        queues[1].add(3);
        queues[2].add(5);

        while (p < n){
            int[] peekNums = {queues[0].peek(),queues[1].peek(),queues[2].peek()};
            int min = Math.min(Math.min(peekNums[0],peekNums[1]),peekNums[2]);
            for (int i = 0; i < peekNums.length; i++) {
                if (peekNums[i] == min){
                    queues[i].remove();
                }
            }
            result = min;
            p++;
            for (int i = 0; i < queues.length; i++) {
                queues[i].add(min * bases[i]);
            }
        }

        return result;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.nthUglyNumber2(10));
    }
}
