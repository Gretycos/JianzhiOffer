package lc.hot100.Q1to25.Q48;

import java.util.Arrays;

/**
 * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 *
 *
 * 示例 1：
 *  [[1 2 3]        [[7 4 1]
 *   [4 5 6]   ->    [8 5 2]
 *   [7 8 9]]        [9 6 3]]
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 *
 * 示例 2：
 *  [[5,1,9,11],        [[15,13,2,5],
 *   [2,4,8,10],    ->   [14,3,4,1],
 *   [13,3,6,7],         [12,6,8,9],
 *   [15,14,12,16]]      [16,7,10,11]]
 *
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 *
 * 提示：
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 *
 * */

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) return;
        for(int row = 0; row <= (n - 1)/2; row++){
            for (int col = row; col < n - row - 1; col++){
                int t = matrix[row][col];
                matrix[row][col] = matrix[n-col-1][row];
                matrix[n-col-1][row] = matrix[n-row-1][n-col-1];
                matrix[n-row-1][n-col-1] = matrix[col][n-row-1];
                matrix[col][n-row-1] = t;
            }
        }
    }

    // 上下对称：matrix[i][j] -> matrix[n-i-1][j]，（列不变）
    // 左右对称：matrix[i][j] -> matrix[i][n-j-1]，（行不变）
    // 主对角线对称：matrix[i][j] -> matrix[j][i]，（行列互换）
    // 副对角线对称：matrix[i][j] -> matrix[n-j-1][n-i-1] （行列均变，且互换）
    //
    // 顺时针旋转90度：matrix[i][j] -> matrix[j][n-i-1]
    // == 先主对角线对称，再左右对称 or 先上下对称，再主对角线对称
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) return;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = t;
            }
        }
    }

}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {
                {1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}
        };
        solution.rotate2(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
