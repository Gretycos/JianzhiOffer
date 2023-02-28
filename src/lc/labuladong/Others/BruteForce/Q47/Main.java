package lc.labuladong.Others.BruteForce.Q47;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    private List<List<Integer>> res;
    private LinkedList<Integer> track;
    private boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        used = new boolean[nums.length];

        // 先排序，让相同的元素靠在一起
        Arrays.sort(nums);
        backtrack(nums);
        return res;
    }

    private void backtrack(int[] nums){
        if (track.size() == nums.length){
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]){
                continue;
            }
            // 新添加的剪枝逻辑，固定相同的元素在排列中的相对位置
            if (i > 0 && nums[i] == nums[i-1] && !used[i-1]){
                continue;
            }
            track.addLast(nums[i]);
            used[i] = true;
            backtrack(nums);
            track.removeLast();
            used[i] = false;
        }
    }
}

public class Main {
}
