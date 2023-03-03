package lc.labuladong.Others.Math.Q268;

import java.util.Arrays;

class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        long total = n * (n+1) / 2;
        long sum = Arrays.stream(nums).sum();
        return (int) (total - sum);
    }

    public int missingNumber2(int[] nums) {
        int n = nums.length;
        int res = 0;
        // 先和新补的索引异或一下
        res ^= n;
        // 和其他的元素、索引做异或
        for (int i = 0; i < n; i++) {
            res ^= i ^ nums[i];
        }
        return res;
    }
}

public class Main {
}
