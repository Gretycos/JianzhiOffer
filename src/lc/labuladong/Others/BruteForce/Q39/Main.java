package lc.labuladong.Others.BruteForce.Q39;

import java.util.LinkedList;
import java.util.List;

class Solution {
    private List<List<Integer>> res;
    private LinkedList<Integer> track;
    private int trackSum;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        trackSum = 0;
        if (candidates.length == 0){
            return res;
        }
        backtrack(candidates, 0, target);
        return res;
    }

    private void backtrack(int[] candidates, int start, int target){
        // base case，找到目标和，记录结果
        if (trackSum == target){
            res.add(new LinkedList<>(track));
            return;
        }
        // base case，超过目标和，停止向下遍历
        if (trackSum > target){
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            trackSum += candidates[i];
            track.addLast(candidates[i]);
            // 递归遍历下一层回溯树
            // 同一元素可重复使用，注意参数
            backtrack(candidates, i, target);
            trackSum -= candidates[i];
            track.removeLast();
        }
    }
}
public class Main {
}
