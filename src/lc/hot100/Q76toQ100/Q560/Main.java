package lc.hot100.Q76toQ100.Q560;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数k ，请你统计并返回 该数组中和为k的连续子数组的个数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 *
 *
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 *
 * */

class Solution {
    // 不能用滑动窗口，因为有负数，不能保证窗口内的和会增加
    public int subarraySum(int[] nums, int k) {
        int res = 0, preSum = 0; // 前缀和
        // 和 : 出现的次数
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for (int num : nums) {
            preSum += num;
            if (map.containsKey(preSum - k)){
                res += map.get(preSum - k);
            }
            map.put(preSum, map.getOrDefault(preSum,0) + 1);
        }
        return res;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-1,-1,1};
        System.out.println(solution.subarraySum(nums,0));
    }
}
