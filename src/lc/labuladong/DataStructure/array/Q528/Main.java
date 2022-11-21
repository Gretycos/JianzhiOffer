package lc.labuladong.DataStructure.array.Q528;

import java.util.Random;

class Solution {
    private int[] preSum;
    private Random random;

    public Solution(int[] w) {
        random = new Random();
        int n = w.length;
        preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + w[i - 1];
        }
    }

    public int pickIndex() {
        int n = preSum.length;
        // nextInt: [0 ~ n-1]
        // target: [1 ~ preSum[n - 1]]
        int target = random.nextInt(preSum[n - 1]) + 1;
        return leftBound(preSum, target) - 1; // 前缀和与w有1位索引偏移
    }

    private int leftBound(int[] nums, int target){
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target){
                right = mid - 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }
        }
        return left;
    }
}
public class Main {
}
