package lc.labuladong.DynamicProgress.greedy.Q55;

class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int farthest = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest <= i){
                return false;
            }
        }
        return farthest >= n - 1;
    }
}

public class Main {
}
