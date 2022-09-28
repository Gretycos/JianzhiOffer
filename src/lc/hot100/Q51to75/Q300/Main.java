package lc.hot100.Q51to75.Q300;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 *
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 *
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 *
 *
 * 进阶：
 *
 * 你能将算法的时间复杂度降低到O(n log(n)) 吗?
 *
 * */

class Solution {
    // dp
    // 定义：dp[i]=以nums[i]结尾的最长上升子序列长度
    // 转移：dp[i] = max{dp[j]+1}, 其中 j < i &&  nums[j] < nums[i]
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    // 贪心+二分
    // 定义：tails[i]=长度为i+1的子序列的尾部元素值
    // 转移：如果区间存在tails[i] > nums[k]，则查找第一个满足tails[i] > nums[k]的数，更新tails[i] = nums[k]
    //      如果不存在，则说明nums[k]可接在前面所有长度的子序列之后，res++
    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];
        int res = 0;
        for(int num: nums){
            // 在tails中二分查找第一个比num大的数
            int l = 0, r = res;
            while (l < r){
                int m = l + (r - l) / 2;
                if (tails[m] < num){
                    l = m+1;
                }else{
                    r = m;
                }
            }
            // 找到的时候l指向tails中第一个比num大的数
            // 找不到的时候l指向res
            tails[l] = num;
            // 右指针没变过，说明num比所有的数都大
            if (res == r){
                res++;
            }
        }
        return res;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(solution.lengthOfLIS2(nums));
    }
}
