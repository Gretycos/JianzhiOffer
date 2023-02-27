package lc.daily.date2302.Q1144;

class Solution {
    public int movesToMakeZigzag(int[] nums) {
        return Math.min(movesToMakeZigzag(nums, 0), movesToMakeZigzag(nums, 1));
    }

    private int movesToMakeZigzag(int[] nums, int start){
        int res = 0;
        for (int i = start; i < nums.length; i+=2) {
            int count = 0;
            if (i - 1 >= 0){
                count = Math.max(count, nums[i] - nums[i-1] + 1) ;
            }
            if (i + 1 < nums.length){
                count = Math.max(count, nums[i] - nums[i+1] + 1) ;
            }
            res += count;
        }
        return res;
    }
}

public class Main {
}
