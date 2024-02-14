package lc.dp.Q55;

/**
 * @Author Tsong
 * @Date 2023/9/15 21:21
 */
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        for (int i = 1; i < n - 1; i++) {
            if (max < i) return false; // 最远都到不了当前位置，返回false
            max = Math.max(max, i + nums[i]);
            if (max >= n - 1){
                return true;
            }
        }
        return max >= n - 1;
    }
}

public class Main {

}
