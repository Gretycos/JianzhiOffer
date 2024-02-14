package lc.dp.Q368;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Tsong
 * @Date 2024/2/12 10:17
 */

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        int[] prev = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);
        int maxSize = 1, maxIdx = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (f(nums[i], nums[j]) && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > maxSize){
                maxSize = dp[i];
                maxIdx = i;
            }
        }

        List<Integer> res = new ArrayList<>();
        while (maxIdx != -1) {
            res.add(nums[maxIdx]);
            maxIdx = prev[maxIdx];
        }
        return res;
    }

    private boolean f(int i, int j) {
        return i % j == 0;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,4,8};
        solution.largestDivisibleSubset(nums);
    }
}
