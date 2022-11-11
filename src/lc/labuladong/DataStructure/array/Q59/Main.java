package lc.labuladong.DataStructure.array.Q59;

/**
 * 给你一个正整数n ，生成一个包含 1 到n2所有元素，且元素按顺时针顺序螺旋排列的n x n 正方形矩阵 matrix 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= n <= 20
 *
 * */
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        // 四个边界
        int upper = 0, lower = n - 1, left = 0, right = n - 1;
        // 要填的数字
        int num = 1;
        while (num <= n * n){
            if (upper <= lower){
                for (int j = left; j <= right; j++) {
                    matrix[upper][j] = num++;
                }
                upper++;
            }

            if (left <= right){
                for (int i = upper; i <= lower; i++) {
                    matrix[i][right] = num++;
                }
                right--;
            }

            if (upper <= lower){
                for (int j = right; j >= left ; j--) {
                    matrix[lower][j] = num++;
                }
                lower--;
            }

            if (left <= right){
                for (int i = lower; i >= upper; i--) {
                    matrix[i][left] = num++;
                }
                left++;
            }
        }
        return matrix;
    }
}
public class Main {
}
