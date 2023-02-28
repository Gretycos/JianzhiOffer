package lc.labuladong.Others.BruteForce.Q78;

import java.util.LinkedList;
import java.util.List;

class Solution {
    private List<List<Integer>> res;
    private LinkedList<Integer> track;
    public List<List<Integer>> subsets(int[] nums) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        backtrack(nums, 0);
        return res;
    }

    private void backtrack(int[] nums, int start){
        // 前序位置，每个节点的值都是一个子集
        res.add(new LinkedList<>(track));

        // 回溯算法标准框架
        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.addLast(nums[i]);
            // 通过 start 参数控制树枝的遍历，避免产生重复的子集
            backtrack(nums, i+1);
            // 撤销选择
            track.removeLast();
        }
    }
}

public class Main {
}
