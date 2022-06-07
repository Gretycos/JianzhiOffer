package com.algorithm.chapter2.jianzhi13;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
 * 请问该机器人能够到达多少个格子？
 *
 *
 *
 * 示例 1：
 *
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 *
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 *
 * 1 <= n,m <= 100
 * 0 <= k<= 20
 *
 * */

class Solution {
    // 方向矩阵
    private final int[][] direct = {
            {-1,0},
            {0,1},
            {1,0},
            {0,-1}
    };
    // 访问矩阵
    private boolean[][] isVisited;

    // 计数
    private int count;

    private int getDigitSum(int m, int n){
        int sum = 0;
        while (m > 0){
            sum += m % 10; // 得到最后一位
            m /= 10; // 去掉最后一位
        }
        while (n>0){
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    private void dfs(int m, int n, int k, int i, int j){
        // 如果能走
        if (i >= 0 && i < m && j >= 0 && j < n && getDigitSum(i,j) <= k && !isVisited[i][j]){
            isVisited[i][j] = true;
            count += 1;

            // 再走四个方向
            for (int d = 0; d < 4; d++) {
                int x = i + direct[d][0];
                int y = j + direct[d][1];

                dfs(m,n,k,x,y);
            }
        }
    }


    // 基于dfs
    // 用bfs也可以
    public int movingCount(int m, int n, int k) {
        isVisited = new boolean[m][n];
        count = 0;
        dfs(m,n,k,0,0);
        return count;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.movingCount(2,3,1));
    }
}
