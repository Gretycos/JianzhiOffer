package lc.labuladong.Others.Math.Q645;

class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int dup = -1;
        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0){
                dup = Math.abs(nums[i]);
                break;
            } else {
                nums[index] *= -1;
            }
        }

        int missing = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0){
                missing = i + 1;
                break;
            }
        }
        return new int[]{dup, missing};
    }
}

public class Main {
}
