package lc.labuladong.Others.Classic.Q42;

class Solution{
    public int trap(int[] height){
        int n = height.length;
        if (n == 0) return 0;

        // 数组充当备忘录
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = height[0];
        rightMax[n - 1] = height[n - 1];

        // 从左向右计算 l_max
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        // 从右向左计算 r_max
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        // 计算答案
        int res = 0;
        for (int i = 1; i < n-1; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }

    public int trap2(int[] height){
        int left = 0, right = height.length - 1;
        int lMax = 0, rMax = 0;

        int res = 0;
        while (left < right){
            lMax = Math.max(lMax, height[left]);
            rMax = Math.max(rMax, height[right]);

            // res += min(l_max, r_max) - height[i]
            if (lMax < rMax){
                res += lMax - height[left];
                left++;
            } else {
                res += rMax - height[right];
                right--;
            }
        }

        return res;
    }
}
public class Main {
}
