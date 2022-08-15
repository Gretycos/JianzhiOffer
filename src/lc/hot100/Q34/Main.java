package lc.hot100.Q34;

/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 *
 * 你必须设计并实现时间复杂度为O(log n)的算法解决此问题。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * 提示：
 * 0 <= nums.length <= 10^5
 * -10^9<= nums[i]<= 10^9
 * nums是一个非递减数组
 * -10^9<= target<= 10^9
 * */

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1,-1};
        if (nums.length == 0) return res;
        int l = 0, r = nums.length - 1;
        // l == r结束
        while(l < r){
            // 下取整，说明只有2个元素的时候m与l重合
            int m = l + (r - l) / 2;
            if (nums[m] < target){
                l = m + 1; // 如果l=m会死循环
            }else{
                r = m; // 如果r = m - 1会越界
            }
        }
        if (nums[l] != target) return res;
        res[0] = l;

        l = 0; r = nums.length - 1;
        while(l < r){
            // 上取整，只有2个元素的时候m与r重合
            int m = l + (r - l + 1) / 2;
            if (nums[m] > target){
                r = m - 1;
            }else{
                l = m;
            }
        }
        res[1] = r;
        return res;
    }

}

public class Main {
}
