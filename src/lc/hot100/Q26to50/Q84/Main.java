package lc.hot100.Q26to50.Q84;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 *
 *
 * 示例 1:
 *
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * 示例 2：
 *
 *
 *
 * 输入： heights = [2,4]
 * 输出： 4
 *
 *
 * 提示：
 *
 * 1 <= heights.length <=10^5
 * 0 <= heights[i] <= 10^4
 *
 * */

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 1){
            return heights[0];
        }
        int area = 0;

        // 增加2个哨兵，避免对头尾特殊情况的讨论
        int[] newHeights = new int[n+2];
        System.arraycopy(heights, 0, newHeights, 1, n);
        heights = newHeights;
        n += 2;

        // 单调栈
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);
        for (int i = 1; i < n; i++) {
            // 遇到比栈顶元素小的元素，就可以计算栈顶元素为高的最大面积了
            // 因为此时可以确定栈顶元素不能继续向右扩散
            while (heights[stack.getLast()] > heights[i]){
                int height = heights[stack.removeLast()];
                // 相同元素直接弹出
                while (heights[stack.getLast()] == height){
                    stack.removeLast();
                }
                int width = i - stack.getLast() - 1;
                area = Math.max(area, width * height);

            }
            stack.addLast(i);
        }
        return area;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] heights = {2,4};
        System.out.println(solution.largestRectangleArea(heights));
    }
}
