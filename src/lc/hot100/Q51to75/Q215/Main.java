package lc.hot100.Q51to75.Q215;

import java.util.Random;

/**
 *给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 *
 *
 * 示例2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 10^5
 * -10^4<= nums[i] <= 10^4
 *
 *  */

class Solution {
    private int res;
    private Random random;
    // 最坏情况 T:O(n^2), 期望 T:(n)
    public int findKthLargest(int[] nums, int k) {
        random = new Random();
        fastSelect(nums,0,nums.length - 1, nums.length - k);
        return res;
    }
    private void fastSelect(int[] nums, int start, int end, int k){
        if (end < start){
            return;
        }

        int l = start, r = end;

        int rIdx = random.nextInt(end - start + 1) + start;
        int t = nums[l];
        nums[l] = nums[rIdx];
        nums[rIdx] = t;

        int pivot = nums[l];
        while(l < r){
            while (l < r && nums[r] >= pivot){
                r--;
            }
            nums[l] = nums[r];
            while (l < r && nums[l] <= pivot){
                l++;
            }
            nums[r] = nums[l];
        }
        nums[l] = pivot;
        if (l == k){
            res = nums[l];
            return;
        }
        if (l < k){
            fastSelect(nums,l+1,end,k);
        } else {
            fastSelect(nums,start,l-1, k);
        }

    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3,4,5,5};
        int k = 2;
        System.out.println(solution.findKthLargest(nums,k));
    }
}
