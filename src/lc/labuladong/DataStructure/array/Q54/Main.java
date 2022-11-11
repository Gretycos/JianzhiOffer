package lc.labuladong.DataStructure.array.Q54;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 * */

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // 四个边界
        int upper = 0, lower = m - 1, left = 0, right = n - 1;
        List<Integer> res = new ArrayList<>();
        while (res.size() < m * n){
            // 左到右
            // 前提是upper <= lower，也就是说边界中间还存在至少一行
            if (upper <= lower){
                for (int j = left; j <= right; j++) {
                    res.add(matrix[upper][j]);
                }
                upper++;
            }

            // 上到下
            // 前提是边界中间至少还有一列
            if (left <= right){
                for (int i = upper; i <= lower ; i++) {
                    res.add(matrix[i][right]);
                }
                right--;
            }

            // 右到左
            // 前提是边界中间至少还存在一行
            if (upper <= lower){
                for (int j = right; j >= left; j--){
                    res.add(matrix[lower][j]);
                }
                lower--;
            }

            // 下到上
            // 前提是边界中间至少还存在一列
            if (left <= right){
                for (int i = lower; i >= upper; i--){
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        return res;
    }
}
public class Main {
}
