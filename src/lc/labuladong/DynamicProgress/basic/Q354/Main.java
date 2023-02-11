package lc.labuladong.DynamicProgress.basic.Q354;

import java.util.Arrays;

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        Arrays.sort(envelopes,(e1,e2) ->
                e1[0] != e2[0]? e1[0] - e2[0] : e2[1] - e1[1]);
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);
    }

    private int lengthOfLIS(int[] nums){
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
