package lc.daily.date2212.Q1760;


class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        // 分割出来的数组的最大值 <= 原数组里的最大值
        int min = 1, max = 1;
        for (int num : nums) {
            if (num > max) max = num;
        }
        // 搜索左边界
        int l = min, r = max;
        while (l <= r){
            int mid = l + (r - l) / 2;
            long opts = getOpts(nums,mid);
            if (opts > maxOperations){
                l = mid + 1;
            }else if (opts < maxOperations){
                r = mid - 1;
            }else if (opts == maxOperations){
                r = mid - 1;
            }
        }
        return l;
    }

    private long getOpts(int[] nums, int maxInBags){
        long opts = 0;
        // 假设最大值是maxInBags
        // 那么对于nums中的每一个num，需要多少次操作把num分割成小于等于maxInBags的两个数？
        // 如果0 < num <= maxInBags, opts += 0
        // 如果1 * maxInBags < num <= 2 * maxInBags, opts += 1
        // ...
        // opts += (num - 1) / maxInBags
        for (int num : nums) {
            opts += (num - 1) / maxInBags;
        }
        return opts;
    }
}

public class Main {
}
