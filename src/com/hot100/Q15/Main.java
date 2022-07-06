package com.hot100.Q15;


import java.util.*;

/**
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？
 * 请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 *
 * */
class Solution {

    // 时间O(n^2)
    public List<List<Integer>> threeSum(int[] nums) {
        // 排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        // k是当前最小值的指针
        for(int k = 0; k < nums.length - 2; k++){
            // 最小的数大于0，直接退出
            if (nums[k] > 0) break;
            // 跳过相等的最小数
            if (k > 0 && nums[k] == nums[k-1]) continue;
            // 左右指针
            int l = k+1, r = nums.length-1;
            while(l < r){
                int sum = nums[k] + nums[l] + nums[r];
                // 和太小，左指针++
                if (sum < 0){
                    l++;
                // 和太大，右指针--
                } else if (sum > 0) {
                    r--;
                // 和=0
                } else {
                    List<Integer> temp = new ArrayList<>(Arrays.asList(nums[k],nums[l],nums[r]));
                    res.add(temp);
                    // 去重
                    while(l < r && nums[l] == nums[++l]);
                    while(l < r && nums[r] == nums[--r]);
                }
            }
        }
        return res;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3,0,-2,-1,1,2};
        System.out.println(solution.threeSum(nums));
    }
}
