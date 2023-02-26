package lc.labuladong.Others.BruteForce.Q46;

import java.util.LinkedList;
import java.util.List;

class Solution {
    private int[] nums;
    private LinkedList<Integer> track;
    private boolean[] used;
    private List<List<Integer>> res;
    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        track = new LinkedList<>();
        used = new boolean[nums.length];
        res = new LinkedList<>();
        backtrack();
        return res;
    }

    private void backtrack(){
        // 触发结束条件
        if (track.size() == nums.length){
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]){
                // 做选择
                track.add(nums[i]);
                used[i] = true;
                // 进入下一层决策树
                backtrack();
                // 撤销选择
                track.removeLast();
                used[i] = false;
            }
        }
    }
}

public class Main {
}
