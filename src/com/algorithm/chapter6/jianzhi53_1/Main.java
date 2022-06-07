package com.algorithm.chapter6.jianzhi53_1;

/**
 * 统计一个数字在排序数组中出现的次数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 10^5
 * -10^9<= nums[i]<= 10^9
 * nums是一个非递减数组
 * -10^9<= target<= 10^9
 *
 * */

class Solution {

    public int search(int[] nums, int target) {
       int n = nums.length;
       if (n == 0) {
           return 0;
       }
       int left = -1, right = -1;

       // 找左边第一个位置
       int l = 0, r = n-1;
       while(l <= r){
           int mid = l + (r - l) / 2;
           if (target < nums[mid]){
               r = mid - 1;
           }else if (target > nums[mid]){
               l = mid + 1;
           } else {
               // 如果mid左边没有相等的，那么左边界就是mid
               if ((mid > 0 && nums[mid-1] != target) || mid == 0){
                   l = mid;
                   break;
               }else{ // mid左边还有相等的，则左边界不动，右边界--
                   r = mid - 1;
               }
           }
       }
       if (l <= n - 1 && target != nums[l]){
           return 0;
       }
       left = l;


       // 找右边最后一个位置
        l = 0; r = n-1;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if (target < nums[mid]){
                r = mid - 1;
            }else if (target > nums[mid]){
                l = mid + 1;
            }else{
                // 如果mid右边没有相等的，那么右边界就是mid
                if ((mid < n-1 && nums[mid+1] != target) || mid == n-1){
                    r = mid;
                    break;
                }else{ // mid右边还有相等的，则右边界不动，左边界++
                    l = mid + 1;
                }
            }
        }
        if (r >= 0 && target != nums[r]){
            return 0;
        }
        right = r;

        return right - left  + 1;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2,2};
        int target = 8;
        System.out.println(solution.search(nums,target));
    }
}
