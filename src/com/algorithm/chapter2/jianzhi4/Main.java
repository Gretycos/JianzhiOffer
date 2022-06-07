package com.algorithm.chapter2.jianzhi4;

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target=5，返回true。
 *
 * 给定target=20，返回false。
 *
 *
 *
 * 限制：
 *
 * 0 <= n <= 1000
 *
 * 0 <= m <= 1000
 *
*/
class Solution {
    private boolean find(int[][] matrix, int target, int x1, int x2, int y1, int y2){
        // 中心
        int x = (x1 + x2) / 2;
        int y = (y1 + y2) / 2;

        if (target < matrix[x][y]){ // 找左上
            if (x-1 >= x1 && y-1 >= y1){ // 有左上
                return find(matrix,target,x1,x-1,y1,y2) || find(matrix,target,x,x2,y1,y-1);
            }else if (x-1 >= x1){ // 只有上边
                return find(matrix,target,x1,x-1,y1,y2);
            }else if (y-1 >= y1){ // 只有左边
                return find(matrix,target,x,x2,y1,y-1);
            }else // 没有左上
                return false;
        }else if (target > matrix[x][y]){
            if (x+1 <= x2 && y+1 <= y2){ // 有右下
                return find(matrix,target,x+1,x2,y1,y2) || find(matrix,target,x1,x,y+1,y2);
            }else if (x+1 <= x2){ // 只有下边
                return find(matrix,target,x+1,x2,y1,y2);
            }else if (y+1 <= y2){ // 只有右边
                return find(matrix,target,x1,x,y+1,y2);
            }else // 没有右下
                return false;
        }else // 找到
            return true;
    }
    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        if (matrix.length-1 < 0 || matrix[0].length-1 < 0){
            return false;
        }
        return find(matrix,target,0, matrix.length-1,0,matrix[0].length-1);
//        else {
//            int row = 0;
//            int column = matrix[0].length - 1;
//            while (row<matrix.length && column>=0){
//                if (matrix[row][column] == target)
//                    return true;
//                else if (matrix[row][column]>target)
//                    column--;
//                else row++;
//            }
//            return false;
//        }
    }
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length-1 < 0 || matrix[0].length-1 < 0){
            return false;
        }
        int row = 0;
        int column = matrix[0].length - 1;
        while (row < matrix.length && column >= 0){
            if (matrix[row][column] == target)
                return true;
            else if (matrix[row][column] > target)
                column--;
            else row++;
        }
        return false;
    }

}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        int target = 5;
//        int [][] matrix = {};
//        int target = 0;
        System.out.println(solution.findNumberIn2DArray(matrix,target));
    }
}
