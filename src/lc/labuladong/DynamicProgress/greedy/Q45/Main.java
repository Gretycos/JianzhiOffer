package lc.labuladong.DynamicProgress.greedy.Q45;

class Solution {
    public int jump(int[] nums) {
        int end = 0, farthest = 0;
        int jumps = 0;
        for (int i = 0; i < nums.length; i++) {
            farthest = Math.max(farthest, nums[i] + i);
            if (end == i){
                // 跳一次
                jumps ++;
                // 把终点更新成最远
                end = farthest;
            }
        }
        return jumps;
    }
}

public class Main {
}
