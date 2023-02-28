package lc.labuladong.Others.BruteForce.Q90;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    private List<List<Integer>> res;
    private LinkedList<Integer> track;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        // 先排序，让相同的元素靠在一起
        Arrays.sort(nums);
        backtrack(nums, 0);
        return res;
    }

    private void backtrack(int[] nums, int start){
        res.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            // 剪枝逻辑，值相同的相邻树枝，只遍历第一条
            if (i > start && nums[i] == nums[i-1]){
                continue;
            }
            track.addLast(nums[i]);
            backtrack(nums, i+1);
            track.removeLast();
        }
    }
}
public class Main {
}
