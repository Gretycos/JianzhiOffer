package lc.daily.date2209.Q698;

import java.util.Arrays;

/**
 * 给定一个整数数组nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 *
 *
 *
 * 示例 1：
 *
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 *
 *
 * 示例 2:
 *
 * 输入: nums = [1,2,3,4], k = 3
 * 输出: false
 *
 *
 * 提示：
 *
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 * 每个元素的频率在 [1,4] 范围内
 *
 * */

class Solution {
    private int[] nums;
    private int[] sums; // 存储k个组每个组的和
    private int avg; // 每个组应达到的和

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) return false;
        Arrays.sort(nums);
        int n = nums.length;
        avg = sum / k;
        if (nums[n - 1] > avg) return false;
        sums = new int[k];
        this.nums = nums;
        return dfs(n-1); // 从后往前
    }

    private boolean dfs(int p){
        // p 是nums的元素指针
        if (p < 0){
            return true;
        }
        for (int i = 0; i < sums.length; i++) {
            if (i > 0 && sums[i] == sums[i-1]){
                continue;
            }

            if(sums[i] + nums[p] > avg){
                continue;
            }

            // 尝试nums[p]添加到集合i中
            sums[i] += nums[p];
            // 尝试添加nums的前一个数字
            if (dfs(p-1)){
                return true;
            }
            sums[i] -= nums[p];
        }
        return false;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {4,3,2,3,5,2,1};
        int k = 4;
        System.out.println(solution.canPartitionKSubsets(nums,k));
    }
}
