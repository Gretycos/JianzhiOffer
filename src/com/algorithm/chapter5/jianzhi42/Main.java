package com.algorithm.chapter5.jianzhi42;

/**
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 *
 * 示例1:
 *
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为  6。
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 *
 * */

class Solution {
    public int maxSubArray(int[] nums) {
//        int[] f = new int[nums.length];
//        int  max = Integer.MIN_VALUE;
//        for (int i = 0; i < nums.length; i++) {
//            if (i == 0 || f[i-1] < 0){
//                f[i] = nums[i];
//            } else {
//                f[i] = f[i-1] + nums[i];
//            }
//            if (f[i] > max){
//                max = f[i];
//            }
//        }
//        return max;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (sum <= 0) {
                sum = num;
            } else {
                sum += num;
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-2,-1,-3,-4,-1,-2,-1,-5,-4};
        System.out.println(solution.maxSubArray(nums));
    }
}
