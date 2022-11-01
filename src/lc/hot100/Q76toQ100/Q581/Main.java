package lc.hot100.Q76toQ100.Q581;


/**
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，
 * 那么整个数组都会变为升序排序。
 *
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 *
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：0
 *
 *
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 *
 *
 * 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
 *
 * */
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int LMax = Integer.MIN_VALUE, RMin = Integer.MAX_VALUE;
        int l = -1, r = -1;
        int n = nums.length;
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            if (nums[i] >= LMax){ // 记录左边的最大值
                LMax = nums[i];
            }else{
                r = i; // 更新r为比左边最大值小的下标
            }
            if (nums[j] <= RMin){ // 记录右边最小值
                RMin = nums[j];
            }else{
                l = j; // 更新i为比右边最小值大的下标
            }
        }

        return r == -1 ? 0 : r - l + 1;
    }
}


public class Main {
}
