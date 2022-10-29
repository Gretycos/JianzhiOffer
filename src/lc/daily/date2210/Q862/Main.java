package lc.daily.date2210.Q862;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。
 * 如果不存在这样的 子数组 ，返回 -1 。
 *
 * 子数组 是数组中 连续 的一部分。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [1], k = 1
 * 输出：1
 *
 * 示例 2：
 * 输入：nums = [1,2], k = 4
 * 输出：-1
 *
 * 示例 3：
 * 输入：nums = [2,-1,2], k = 3
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * 1 <= k <= 10^9
 *
 * */

class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] sum = new long[n + 1]; // 前缀和
        for (int i = 0; i < n; i++) {
            if (nums[i] >= k) return 1;
            sum[i + 1] = sum[i] + nums[i];
        }
        Deque<Integer> queue = new ArrayDeque<>();
        int res = n + 1;
        for (int i = 0; i <= n; i++) {
            // 如果当前元素-队头元素 >= k，则找到了当前最短的子数组，移除队头元素
            while (!queue.isEmpty() && sum[i] - sum[queue.getFirst()] >= k){
                res = Math.min(res, i - queue.removeFirst());
            }
            // 移除队尾中比当前元素大的元素，因为若存在元素s[x]-s[i]>= k, x > i，x-i一定小于x-j(j是队列中的下标)
            while(!queue.isEmpty() && sum[i] <= sum[queue.getLast()]){
                queue.removeLast();
            }
            queue.addLast(i);
        }
        return res == n + 1 ? -1 : res;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {58,-27,-11,63,90,83,61,-44,-39,30};
        System.out.println(solution.shortestSubarray(nums,61));
    }
}
