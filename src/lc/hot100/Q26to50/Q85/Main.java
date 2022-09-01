package lc.hot100.Q26to50.Q85;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个仅包含0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 *
 *
 * 示例 1：
 *
 *  [["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]]
 *
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 示例 2：
 *
 * 输入：matrix = []
 * 输出：0
 * 示例 3：
 *
 * 输入：matrix = [["0"]]
 * 输出：0
 * 示例 4：
 *
 * 输入：matrix = [["1"]]
 * 输出：1
 * 示例 5：
 *
 * 输入：matrix = [["0","0"]]
 * 输出：0
 *
 *
 * 提示：
 *
 * rows == matrix.length
 * cols == matrix[0].length
 * 1 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 *
 * */

class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n];
        int max = 0;
        for (char[] row : matrix) {
            for (int col = 0; col < n; col++) {
                heights[col] = row[col] == '1' ? heights[col] + 1 : 0; // 遍历到'0'直接重置高度为0
            }
            max = Math.max(max,largestRectangleArea(heights));
        }
        return max;
    }

    private int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 1) return heights[0];
        int area = 0;

        int[] newHeights = new int[n+2];
        System.arraycopy(heights,0,newHeights,1,n);
        heights = newHeights;
        n += 2;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);
        for (int i = 1; i < n; i++) {
            while (heights[stack.peekLast()] > heights[i]){
                int height = heights[stack.removeLast()];
                while(heights[stack.peekLast()] == height){
                    stack.removeLast();
                }
                int width = i - stack.peekLast() - 1;
                area = Math.max(area,height * width);
            }
            stack.addLast(i);
        }
        return area;
    }
}

public class Main {
}
