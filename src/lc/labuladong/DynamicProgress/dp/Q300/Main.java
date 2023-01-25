package lc.labuladong.DynamicProgress.dp.Q300;

import java.util.Arrays;

class Solution {
    // dp
    public int lengthOfLIS(int[] nums) {
        // 定义：dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度
        int[] dp = new int[nums.length];
        // base
        // 刚开始的时候，算上自身的长度，所以初始值为1
        Arrays.fill(dp,1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
        }

        int res = 0;
        for (int len : dp) {
            res = Math.max(res, len);
        }
        return res;
    }

    // 二分 不好理解
    public int lengthOfLIS2(int[] nums) {
        int[] top = new int[nums.length];
        // 牌堆数
        int piles = 0;
        for (int poker : nums) {
            // 搜索[0, piles)
            int left = 0, right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else if (top[mid] == poker) {
                    right = mid;
                }
            }
            // 没有适合的牌堆，新建一堆
            if (left == piles) piles++;
            top[left] = poker;
        }
        return piles;
    }
}
public class Main {
}
