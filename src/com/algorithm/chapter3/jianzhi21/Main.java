package com.algorithm.chapter3.jianzhi21;

import java.util.Arrays;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 *
 *
 *
 * 示例：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 50000
 * 0 <= nums[i] <= 10000
 *
 * */

class Solution {
    public int[] exchange(int[] nums) {
        if (nums.length == 0){
            return nums;
        }

        int p1 = 0;
        int p2 = nums.length - 1;

        int t;

        // 快排思想
        while(p1 < p2){
            // 左奇
            // 会找到最左边的一个偶数
            while (p1 < p2 && nums[p1] % 2 != 0){
                p1++;
            }
            // 右偶
            // 会找到最右边的一个奇数
            while (p1 < p2 && nums[p2] % 2 == 0){
                p2--;
            }
            // 偶奇
            // 交换
            if (p1 < p2){
                t = nums[p1];
                nums[p1] = nums[p2];
                nums[p2] = t;
            }
        }

        return nums;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2,16,3,5,13,1,16,1,12,18,11,8,11,11,5,1};
        System.out.println(Arrays.toString(solution.exchange(nums)));
    }
}
