package lc.offer2.chapter5.jianzhi42;

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
    // f[i]是第i个数字结尾的子数组的最大和
    // 因为负数累加会更小，所以f[i]更新成第i个数字本身
    public int maxSubArray2(int[] nums) {
        int f = nums[0], max = f;
        for (int i = 1; i < nums.length; i++) {
            if (f <= 0){
                f = nums[i];
            }else{
                f += nums[i];
            }
            max = Math.max(max,f);
        }
        return max;
    }
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            sum = Math.max(num,sum+num);
            max = Math.max(max,sum);
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
