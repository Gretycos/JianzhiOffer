package lc.daily.date2209.Interview1Q8;

/**
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 *
 *
 *
 * 示例 1：
 *
 * 输入：
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出：
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 *
 *
 * 示例 2：
 *
 * 输入：
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出：
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 *
 * */

class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // 处理首行和首列
        boolean clearFirstRow = false, clearFirstCol = false;
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0){
                clearFirstRow = true;
                break;
            }
        }
        for (int[] row : matrix) {
            if (row[0] == 0) {
                clearFirstCol = true;
                break;
            }
        }
        // 用首行和首列进行标记
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 清零除首行首列的部分
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        // 清零首行首列
        if (clearFirstRow){
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
        if (clearFirstCol){
            for (int[] row : matrix) {
                row[0] = 0;
            }
        }
    }

}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}
        };
        solution.setZeroes(matrix);
    }
}
