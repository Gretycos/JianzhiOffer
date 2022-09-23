package lc.hot100.Q51to75.Q221;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [
 *                ["1","0","1","0","0"],
 *                ["1","0","1","1","1"],
 *                ["1","1","1","1","1"],
 *                ["1","0","0","1","0"]
 *              ]
 * 输出：4
 *
 *
 * 示例 2：
 *
 *
 * 输入：matrix = [
 *                  ["0","1"],
 *                  ["1","0"]
 *               ]
 * 输出：1
 * 示例 3：
 *
 * 输入：matrix = [["0"]]
 * 输出：0
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 *
 * */

class Solution {
    // 最大矩形套用 T:O(mn^2)  S:O(n)
    public int maximalSquare2(char[][] matrix) {
        int n = matrix[0].length;
        int[] heights = new int[n];
        int max = 0;
        for (char[] row : matrix) {
            for (int col = 0; col < n; col++) {
                heights[col] = row[col] == '1' ? heights[col] + 1 : 0;
            }
            max = Math.max(max,largestSquareArea(heights));
        }
        return max;
    }

    private int largestSquareArea(int[] heights) {
        int n = heights.length;
        if (n == 1) return heights[0] == 0? 0 : 1;
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
                int a = Math.min(height,width);
                area = Math.max(area,a * a);
            }
            stack.addLast(i);
        }
        return area;
    }


    // dp
    // 定义：dp(i,j)为以(i,j)为右下角，且只包含1的正方形的边长的最大值
    // 转移：dp(i,j) = min{dp(i-1,j),dp(i,j-1),dp(i-1,j-1)} + 1, if M(i,j) == 1
    //              = 0, else
    // T:O(mn), S:O(mn)
    public int maximalSquare(char[][] matrix) {
        int max = 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1'){
                    if (i == 0 || j == 0){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]) + 1;
                    }
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max * max;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] matrix = {
                {'1'},
                {'0'},
                {'1'},
                {'1'},
                {'1'},
                {'0'}
        };
        System.out.println(solution.maximalSquare(matrix));
    }
}
