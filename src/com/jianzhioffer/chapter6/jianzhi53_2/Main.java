package com.jianzhioffer.chapter6.jianzhi53_2;

/**
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 *
 * 示例 1:
 *
 * 输入: [0,1,3]
 * 输出: 2
 * 示例2:
 *
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 *  
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 10000
 *
 * */

class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int l = 0, r = n-1;
        if (nums[l] != l){
            return l;
        }

        if (nums[r] != n){
            return n;
        }

        while (l <= r){
            int mid = l + (r - l)/2;
            // 中间与下标不相同，找左半边
            if (nums[mid] != mid){
                if (nums[mid-1] == mid - 1){
                    return mid;
                }
                r = mid - 1;
            } else {// 中间和下标相同，找右半边
                l = mid + 1;
            }

        }
        return -1;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0,1,2,4,5,6};
        System.out.println(solution.missingNumber(nums));
    }


}
