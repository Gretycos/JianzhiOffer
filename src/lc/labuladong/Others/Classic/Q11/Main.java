package lc.labuladong.Others.Classic.Q11;

class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            // [left, right] 之间的矩形面积
            int curArea = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, curArea);
            // 双指针技巧，移动较低的一边
            // 因为面积取决于最小的一边
            // 如果移动更大的一边，如果变大，面积不变；如果变小，面积变小
            // 因此不能移动大的一边
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(solution.maxArea(arr));
    }
}
