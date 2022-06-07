package com.algorithm.chapter4.jianzhi29;

import java.util.Arrays;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 * 限制：
 *
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 *
 * */

class Solution {
    public int[] spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return new int[]{};
        }
        int n = matrix[0].length;
        int[] result = new int[m * n];
        int t = 0; // 结果指针

        // 边界
        int l = 0; // 左
        int r = n - 1; // 右
        int u = 0; // 上
        int d = m - 1; // 下

        int i = 0; // 矩阵行指针
        int j = 0; // 矩阵列指针

        while (t < result.length){
            if (i == m / 2 && j == n / 2){
                result[t++] = matrix[i][j];
            }

            while (t < result.length && j < r){ // 右
                result[t++] = matrix[i][j++];
            }

            while (t < result.length && i < d){ // 下
                result[t++] = matrix[i++][j];
            }

            while (t < result.length && j > l){ // 左
                result[t++] = matrix[i][j--];
            }

            while (t < result.length && i > u){ // 上
                result[t++] = matrix[i--][j];
            }

            l++;
            u++;
            r--;
            d--;
            i++;
            j++;
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {16,17,18,19,6},
                {15,24,25,20,7},
                {14,23,22,21,8},
                {13,12,11,10,9}
        };
        System.out.println(Arrays.toString(solution.spiralOrder(matrix)));
    }
}
