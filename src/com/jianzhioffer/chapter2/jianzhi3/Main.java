package com.jianzhioffer.chapter2.jianzhi3;

/**
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 * 限制：
 *
 * 2 <= n <= 100000
 *
*/
class Solution {
    // T: O(n); S: O(n)
//    public int findRepeatNumber(int[] nums) {
//        int[] count = new int[nums.length];
//        for (int num : nums) {
//            if (++count[num] >= 2) {
//                return num;
//            }
//        }
//        return -1;
//    }
    // T:O(n) S:O(1)
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length;) {
            // 元素与下标相等
            if (i == nums[i]){
                i++;
            }else{
                // 元素与下标不相等
                // i所指向的数字与nums[i]所指向的数字相同，说明重复
                if (nums[nums[i]]==nums[i]){
                    return nums[i];
                }else {
                    // 交换，让下标i的元素与i相等
                    int t = nums[nums[i]];
                    nums[nums[i]] = nums[i];
                    nums[i] = t;
                }
            }
        }
        return -1;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = {2,3,1,0,2,5,3};
        System.out.println(solution.findRepeatNumber(a));
    }
}
