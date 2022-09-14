package lc.hot100.Q51to75.Q152;

/**
 * 给你一个整数数组 nums，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 测试用例的答案是一个32-位 整数。
 *
 * 子数组 是数组的连续子序列。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释:子数组 [2,3] 有最大乘积 6。
 *
 *
 * 示例 2:
 *
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释:结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * nums 的任何前缀或后缀的乘积都 保证是一个 32-位 整数
 *
 *
 * */
class Solution {
    public int maxProduct(int[] nums) {
        // 维护一个当前乘积最小值和一个当前乘积最大值
        // 因为当前乘积最小而且为负的时候，再遇到一个负数相乘可得最大值
        int f_min = nums[0], f_max = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++){
            int min = f_min, max = f_max;
            f_max = Math.max(max * nums[i], Math.max(min * nums[i], nums[i]));
            f_min = Math.min(max * nums[i], Math.min(min * nums[i], nums[i]));
            res = Math.max(f_max,res);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-2,4,-3};
        System.out.println(solution.maxProduct(nums));
    }
}
